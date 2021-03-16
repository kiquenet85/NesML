package com.nesml.search_services.repository.search

import android.location.Location
import kotlinx.coroutines.flow.Flow
import java.util.*

interface SearchRemoteSource {

    suspend fun createOrUpdate(accountId: String, items: List<Location>): Boolean

    suspend fun createOrUpdate(accountId: String, items: Location): Boolean

    fun getAll(accountId: String): Flow<List<Location>>

    fun getById(id: String): Flow<Optional<Location>>

    suspend fun deleteById(accountId: String, id: String): Int

    suspend fun deleteAll(accountId: String, items: List<String>): Int
}