package com.nesml.storage.model.search.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index

@Entity(
        foreignKeys = [androidx.room.ForeignKey(
                entity = SearchItem::class,
                parentColumns = ["id"],
                childColumns = ["searchItemId"]
        )],
        indices = [Index(value = ["id"], unique = true),
            Index(value = ["searchItemId"], unique = false),
            Index(value = ["id", "searchItemId"], unique = true)],
        primaryKeys = ["id", "searchItemId"]
)
data class Attribute(
        var id: String,
        var searchItemId: String,
        var name: String,
        var value_name: String? = null,
) {
    @Ignore
    var values: List<AttributeValue>? = null
}
