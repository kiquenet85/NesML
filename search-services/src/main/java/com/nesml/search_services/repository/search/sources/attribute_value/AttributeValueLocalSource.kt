package com.nesml.search_services.repository.search.sources.attribute_value

import com.nesml.commons.util.Optional
import com.nesml.storage.model.search.entity.AttributeValue
import kotlinx.coroutines.flow.Flow

interface AttributeValueLocalSource {

    suspend fun createOrUpdate(items: List<AttributeValue>): Boolean

    fun getAll(): Flow<List<AttributeValue>>

    fun getById(id: String): Flow<Optional<AttributeValue>>

    suspend fun deleteAll(accountId: String, items: List<AttributeValue>): Int
}