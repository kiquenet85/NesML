package com.nesml.search_services.repository.search

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.nesml.commons.util.Optional
import com.nesml.search_services.model.db.SearchModuleDB
import com.nesml.search_services.model.db.entity.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class SearchLocalSourceImp(private val db: RoomDatabase) : SearchLocalSource {

    private val searchModuleDB = db as SearchModuleDB

    override suspend fun createOrUpdate(accountId: String, items: List<SearchItem>): Boolean {
        val oldItemsId = searchModuleDB.searchItemDAO().getAllId(accountId)
        val newItemsId = mutableListOf<String>()
        db.withTransaction {
            items.forEach { toInsert ->
                newItemsId.add(toInsert.id)
                createOrUpdate(accountId, toInsert)
            }

            oldItemsId.forEach { oldItemId ->
                if (!newItemsId.contains(oldItemId)) {
                    searchModuleDB.searchItemDAO().delete(oldItemId)
                }
            }
        }
        return true
    }

    override suspend fun createOrUpdate(accountId: String, item: SearchItem): Boolean {
        if (searchModuleDB.searchItemDAO().update(entityToInsert = item) == 0) {
            searchModuleDB.searchItemDAO().insert(item)
        }
        return true
    }

    override fun getAll(accountId: String): Flow<List<SearchItem>> {
        return searchModuleDB.searchItemDAO().getAll(accountId)
            .filterNotNull()
            .flowOn(Dispatchers.Default)
            .distinctUntilChanged()
            .conflate()
    }

    override fun getById(id: String): Flow<Optional<SearchItem>> {
        return searchModuleDB.searchItemDAO().getById(id)
            .map { if (it == null) Optional.None else Optional.Some(it) }
            .flowOn(Dispatchers.Default)
            .distinctUntilChanged()
            .conflate()
    }

    override suspend fun deleteById(accountId: String, id: String): Int {
        return searchModuleDB.searchItemDAO().delete(id)
    }

    override suspend fun deleteAll(accountId: String, items: List<SearchItem>): Int {
        var deletions = 0
        db.withTransaction {
            items.forEach {
                it.id.let { elementId ->
                    deletions = searchModuleDB.searchItemDAO().delete(elementId)
                }
            }
        }
        return deletions
    }
}