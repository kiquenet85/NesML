package com.nesml.search_services.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @Expose
    @SerializedName("results")
    val searchItems: List<SearchItemDTO>
)
