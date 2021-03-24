package com.nesml.search_services.repository.search.sources

import androidx.room.withTransaction
import com.nesml.commons.util.ACCOUNT_MOCK
import com.nesml.commons.util.Optional
import com.nesml.storage.AppDB
import com.nesml.storage.model.account.entity.Account
import com.nesml.storage.model.search.entity.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchLocalSourceImp @Inject constructor(private val db: AppDB) : SearchLocalSource {

    override suspend fun createOrUpdate(accountId: String, items: List<SearchItem>): Boolean {
        val oldItemsId = db.searchItemDAO().getAllId(accountId)
        val newItemsId = mutableListOf<String>()

        val account = db.accountDAO().getById(ACCOUNT_MOCK).first()
        if (account == null) {
            db.accountDAO().insert(Account(ACCOUNT_MOCK, "Singleton Account"))
        }

        db.withTransaction {
            items.forEach { toInsert ->
                newItemsId.add(toInsert.id)
                toInsert.accountId = accountId
            }

            createOrUpdate(items)

            oldItemsId.forEach { oldItemId ->
                if (!newItemsId.contains(oldItemId)) {
                    db.searchItemDAO().delete(oldItemId)
                }
            }
        }
        return true
    }

    private suspend fun createOrUpdate(items: List<SearchItem>): Boolean {
        db.searchItemDAO().insert(items)
        return true
    }

    override suspend fun createOrUpdate(accountId: String, item: SearchItem): Boolean {
        item.accountId = accountId
        db.searchItemDAO().insert(item)
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