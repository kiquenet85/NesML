package com.nesml.search_services.repository.search.sources

import com.nesml.commons.util.Optional
import com.nesml.storage.model.search.entity.SearchItem
import kotlinx.coroutines.flow.Flow

interface SearchLocalSource {

    suspend fun createOrUpdate(accountId: String, items: List<SearchItem>): Boolean

    suspend fun createOrUpdate(accountId: String, item: SearchItem): Boolean

    fun getAll(accountId: String): Flow<List<SearchItem>>

    fun getById(id: String): Flow<Optional<SearchItem>>

    suspend fun deleteById(accountId: String, id: String): Int

    suspend fun deleteAll(accountId: String, items: List<SearchItem>): Int
}