package com.nesml.search_services.repository.search.sources

import androidx.room.withTransaction
import com.nesml.commons.util.Optional
import com.nesml.storage.AppDB
import com.nesml.storage.model.search.entity.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchLocalSourceImp @Inject constructor(private val db: AppDB) : SearchLocalSource {

    override suspend fun createOrUpdate(accountId: String, items: List<SearchItem>): Boolean {
        val oldItemsId = db.searchItemDAO().getAllId(accountId)
        val newItemsId = mutableListOf<String>()
        db.withTransaction {
            items.forEach { toInsert ->
                newItemsId.add(toInsert.id)
                createOrUpdate(accountId, toInsert)
            }

            oldItemsId.forEach { oldItemId ->
                if (!newItemsId.contains(oldItemId)) {
                    db.searchItemDAO().delete(oldItemId)
                }
            }
        }
        return true
    }

    override suspend fun createOrUpdate(accountId: String, item: SearchItem): Boolean {
        if (db.searchItemDAO().update(entityToInsert = item) == 0) {
            db.searchItemDAO().insert(item)
        }
        return true
    }

    override fun getAll(accountId: String): Flow<List<SearchItem>> {
        return db.searchItemDAO().getAll(accountId)
            .filterNotNull()
            .distinctUntilChanged()
            .conflate()
            .flowOn(Dispatchers.Default)
    }

    override fun getById(id: String): Flow<Optional<SearchItem>> {
        return db.searchItemDAO().getById(id)
            .map { if (it == null) Optional.None else Optional.Some(it) }
            .distinctUntilChanged()
            .conflate()
            .flowOn(Dispatchers.Default)
    }

    override suspend fun deleteById(accountId: String, id: String): Int {
        return db.searchItemDAO().delete(id)
    }

    override suspend fun deleteAll(accountId: String, items: List<SearchItem>): Int {
        var deletions = 0
        db.withTransaction {
            items.forEach {
                it.id.let { elementId ->
                    deletions = db.searchItemDAO().delete(elementId)
                }
            }
        }
        return deletions
    }
}