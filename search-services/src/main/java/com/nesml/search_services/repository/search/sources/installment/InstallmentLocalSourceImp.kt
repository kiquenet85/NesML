package com.nesml.search_services.repository.search.sources.installment

import androidx.room.withTransaction
import com.nesml.commons.util.ACCOUNT_MOCK
import com.nesml.storage.AppDB
import com.nesml.storage.model.search.entity.Installment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InstallmentLocalSourceImp @Inject constructor(private val db: AppDB) : InstallmentLocalSource {

    override suspend fun createOrUpdate(items: List<Installment>): Boolean {
        val a = db.searchItemDAO().getAll(ACCOUNT_MOCK).first()
        val b = a.size

        db.withTransaction {
            db.installmentItemDAO().deleteAll()
            db.installmentItemDAO().insert(items)
        }
        return true
    }

    override fun getAll(): Flow<List<Installment>> {
        return db.installmentItemDAO().getAll()
                .filterNotNull()
                .distinctUntilChanged()
                .conflate()
                .flowOn(Dispatchers.Default)
    }

    override suspend fun deleteAll(searchItemId: String, items: List<Installment>): Int {
        var deletions = 0
        db.withTransaction {
            items.forEach {
                it.id.let { elementId ->
                    deletions = db.installmentItemDAO().delete(elementId)
                }
            }
        }
        return deletions
    }
}