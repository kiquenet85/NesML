package com.nesml.search_services.repository.search

import com.nesml.search_services.api.SearchAPI
import com.nesml.search_services.model.network.SearchItemDTO

class SearchRemoteSourceImp(val searchAPI: SearchAPI) : SearchRemoteSource {

    override suspend fun getAll(query: String): List<SearchItemDTO> {
        return searchAPI.getJobSitesByAccountId(query)
    }
}