package com.nesml.storage.model.search.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nesml.storage.model.account.entity.Account

@Entity(
        foreignKeys = [androidx.room.ForeignKey(
                entity = Account::class,
                parentColumns = ["id"],
                childColumns = ["accountId"]
        )],
        indices = [Index(value = ["id"], unique = true),
                Index(value = ["accountId"], unique = false),
                Index(value = ["id", "accountId"], unique = true)],
)
data class SearchItem(
        @PrimaryKey
        var id: String,
        var accountId: String,
        var site_id: String? = null,
        var title: String? = null,
        var price: Int? = null,
        var sale_price: String? = null,
        var currency_id: String? = null,
        var available_quantity: Int? = null,
        var sold_quantity: Int? = null,
        var buying_mode: String? = null,
        var listing_type_id: String? = null,
        var stop_time: String? = null,
        var condition: String? = null,
        var permalink: String? = null,
        var thumbnail: String? = null,
        var thumbnail_id: String? = null,
        var accepts_mercadopago: Boolean? = null,
        var original_price: Int? = null,
        var category_id: String? = null,
        var official_store_id: String? = null,
        var domain_id: String? = null,
        var catalog_product_id: String? = null
) {
        @Ignore
        var installment: Installment? = null

        @Ignore
        var attributes: List<Attribute>? = null
}
