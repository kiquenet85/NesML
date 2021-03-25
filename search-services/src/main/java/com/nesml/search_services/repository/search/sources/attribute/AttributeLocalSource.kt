package com.nesml.search_services.repository.search.sources.attribute

import com.nesml.storage.model.search.entity.Attribute
import kotlinx.coroutines.flow.Flow

interface AttributeLocalSource {

    suspend fun createOrUpdate(items: List<Attribute>): Boolean

    fun getAll(): Flow<List<Attribute>>

    fun getById(searchItemId: String): Flow<List<Attribute>>

    suspend fun deleteAll(accountId: String, items: List<Attribute>): Int
}