package com.nesml.storage.model.search.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(
        foreignKeys = [androidx.room.ForeignKey(
                entity = SearchItem::class,
                parentColumns = ["id"],
                childColumns = ["searchItemId"]
        )],
        indices = [Index(value = ["id"], unique = true),
            Index(value = ["searchItemId"], unique = true),
            Index(value = ["id", "searchItemId"], unique = true)
        ],
        primaryKeys = ["id", "searchItemId"]
)
data class Installment(
        var id: String,
        var searchItemId: String,
        var quantity: Int? = null,
        var amount: Double? = null,
        var rate: Int? = null,
        var currency_id: String? = null
)
