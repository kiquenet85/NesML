package com.nesml.search_services.repository.search.sources

import com.nesml.search_services.model.network.SearchResponse

interface SearchRemoteSource {
    suspend fun getAll(query: String): SearchResponse
}