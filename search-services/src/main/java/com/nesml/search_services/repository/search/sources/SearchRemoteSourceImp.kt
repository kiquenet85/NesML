package com.nesml.search_services.repository.search.sources

import com.nesml.search_services.api.SearchAPI
import com.nesml.search_services.model.network.SearchResponse
import javax.inject.Inject

class SearchRemoteSourceImp @Inject constructor(val searchAPI: SearchAPI) : SearchRemoteSource {

    override suspend fun getAll(query: String): SearchResponse {
        return searchAPI.getJobSitesByAccountId(query)
    }
}