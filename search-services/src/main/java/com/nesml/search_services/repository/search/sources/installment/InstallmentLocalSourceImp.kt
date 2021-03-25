package com.nesml.search_services.repository.search.sources.installment

import androidx.room.withTransaction
import com.nesml.commons.util.Optional
import com.nesml.storage.AppDB
import com.nesml.storage.model.search.entity.Installment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class InstallmentLocalSourceImp @Inject constructor(private val db: AppDB) :
    InstallmentLocalSource {

    override suspend fun createOrUpdate(items: List<Installment>): Boolean {

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

    override fun getBySearchItemId(searchItemId: String): Flow<Optional<Installment>> {
        return db.installmentItemDAO().getById(searchItemId)
            .map {
                if (it == null) Optional.None
                else Optional.Some(it)
            }
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