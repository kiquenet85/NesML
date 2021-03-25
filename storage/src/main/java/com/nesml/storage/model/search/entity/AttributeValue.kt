package com.nesml.storage.model.search.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(
    foreignKeys = [androidx.room.ForeignKey(
        entity = Attribute::class,
        parentColumns = ["id"],
        childColumns = ["attributeId"]
    ), androidx.room.ForeignKey(
        entity = SearchItem::class,
        parentColumns = ["id"],
        childColumns = ["searchItemId"]
    )],
    indices = [Index(value = ["id"], unique = true),
        Index(value = ["attributeId"], unique = false),
        Index(value = ["searchItemId"], unique = false),
        Index(value = ["id", "attributeId"], unique = true),
        Index(value = ["id", "attributeId", "searchItemId"], unique = true)],
    primaryKeys = ["id", "attributeId"]
)
data class AttributeValue(
    var id: String,
    var attributeId: String,
    var searchItemId: String,
    var name: String? = null
)
