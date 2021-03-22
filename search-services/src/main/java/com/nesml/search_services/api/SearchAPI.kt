package com.nesml.search_services.api

import com.nesml.search_services.model.network.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {

    @GET("/sites/MCO/search")
    suspend fun getJobSitesByAccountId(@Query("q") query: String): SearchResponse

}