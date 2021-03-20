package com.nesml.search_services.repository.search

import com.nesml.search_services.model.network.SearchItemDTO

interface SearchRemoteSource {
    suspend fun getAll(query: String): List<SearchItemDTO>
}