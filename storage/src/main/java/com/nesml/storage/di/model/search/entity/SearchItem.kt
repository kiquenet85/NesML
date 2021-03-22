package com.nesml.storage.di.model.search.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchItem(
    @PrimaryKey
    val id: String,
    val zipcode: String? = null,
    val state: String? = null,
    val created: String? = null,
    val accountId: String? = null,
    val address: String? = null,
    val address2: String? = null,
    val city: String? = null,
    val gpsLong: Double? = null,
    val gpsLat: Double? = null,
    val updated: String? = null,
    val parentId: String? = null,
    val name: String? = null,
    val status: String? = null,
    val budget: Double? = null,
    val jobSiteType: String? = null,
    var isPrevailingWageActive: Int? = null,
    val autoAdjustStartTime: String? = null
)