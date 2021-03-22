package com.nesml.storage.model.search.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(
        foreignKeys = [androidx.room.ForeignKey(
                entity = SearchItem::class,
                parentColumns = ["id"],
                childColumns = ["searchItemId"]
        )],
        indices = [Index(value = ["id", "searchItemId"], unique = true)],
        primaryKeys = ["id", "searchItemId"]
)
data class Values(
        var id: String,
        var searchItemId: String,
        var name: String? = null
)
