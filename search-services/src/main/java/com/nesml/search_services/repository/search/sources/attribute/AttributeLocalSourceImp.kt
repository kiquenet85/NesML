package com.nesml.search_services.repository.search.sources.attribute

import androidx.room.withTransaction
import com.nesml.storage.AppDB
import com.nesml.storage.model.search.entity.Attribute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AttributeLocalSourceImp @Inject constructor(private val db: AppDB) : AttributeLocalSource {

    override suspend fun createOrUpdate(items: List<Attribute>): Boolean {
        db.withTransaction {
            with(db.attributeItemDAO()) {
                deleteAll()
                insert(items)
            }
        }
        return true
    }

    override fun getAll(): Flow<List<Attribute>> {
        return db.attributeItemDAO().getAll()
            .filterNotNull()
            .distinctUntilChanged()
            .conflate()
            .flowOn(Dispatchers.Default)
    }

    override fun getById(searchItemId: String): Flow<List<Attribute>> {
        return db.attributeItemDAO().getById(searchItemId)
            .distinctUntilChanged()
            .conflate()
            .flowOn(Dispatchers.Default)
    }

    override suspend fun deleteAll(accountId: String, items: List<Attribute>): Int {
        var deletions = 0
        db.withTransaction {
            items.forEach {
                it.id.let { elementId ->
                    deletions = db.attributeItemDAO().delete(elementId)
                }
            }
        }
        return deletions
    }
}