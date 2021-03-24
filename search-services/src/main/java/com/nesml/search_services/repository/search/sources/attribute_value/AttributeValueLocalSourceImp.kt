package com.nesml.search_services.repository.search.sources.attribute_value

import androidx.room.withTransaction
import com.nesml.commons.util.Optional
import com.nesml.storage.AppDB
import com.nesml.storage.model.search.entity.AttributeValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AttributeValueLocalSourceImp @Inject constructor(private val db: AppDB) : AttributeValueLocalSource {

    override suspend fun createOrUpdate(items: List<AttributeValue>): Boolean {
        db.withTransaction {
            with(db.attributeValueDAO()) {
                deleteAll()
                insert(items)
            }
        }
        return true
    }

    override fun getAll(): Flow<List<AttributeValue>> {
        return db.attributeValueDAO().getAll()
                .filterNotNull()
                .distinctUntilChanged()
                .conflate()
                .flowOn(Dispatchers.Default)
    }

    override fun getById(id: String): Flow<Optional<AttributeValue>> {
        return db.attributeValueDAO().getById(id)
                .map { if (it == null) Optional.None else Optional.Some(it) }
                .distinctUntilChanged()
                .conflate()
                .flowOn(Dispatchers.Default)
    }

    override suspend fun deleteAll(accountId: String, items: List<AttributeValue>): Int {
        var deletions = 0
        db.withTransaction {
            items.forEach {
                it.id.let { elementId ->
                    deletions = db.attributeValueDAO().delete(elementId)
                }
            }
        }
        return deletions
    }
}