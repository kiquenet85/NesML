package com.nesml.search_services.api

import com.nesml.search_services.model.network.SearchItemDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchAPI {

    @GET("/sites/MCO/search?q={query}#json")
    suspend fun getJobSitesByAccountId(@Path("query") query: String): List<SearchItemDTO>

}