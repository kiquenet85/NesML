package com.nesml.search_services.repository.search.sources.installment

import com.nesml.storage.model.search.entity.Installment
import kotlinx.coroutines.flow.Flow

interface InstallmentLocalSource {

    suspend fun createOrUpdate(items: List<Installment>): Boolean

    fun getAll(): Flow<List<Installment>>

    suspend fun deleteAll(searchItemId: String, items: List<Installment>): Int
}