package com.nesml.search_services.model.network

import com.google.gson.annotations.Expose

data class SearchItemDTO(
    @Expose var id: String,
    @Expose var site_id: String? = null,
    @Expose var title: String? = null,
    @Expose var price: Int? = null,
    @Expose var sale_price: String? = null,
    @Expose var currency_id: String? = null,
    @Expose var available_quantity: Int? = null,
    @Expose var sold_quantity: Int? = null,
    @Expose var buying_mode: String? = null,
    @Expose var listing_type_id: String? = null,
    @Expose var stop_time: String? = null,
    @Expose var condition: String? = null,
    @Expose var permalink: String? = null,
    @Expose var thumbnail: String? = null,
    @Expose var thumbnail_id: String? = null,
    @Expose var accepts_mercadopago: Boolean? = null,
    @Expose var installments: Installments? = null,
    @Expose var attributes: List<Attributes>? = null,
    @Expose var original_price: Int? = null,
    @Expose var category_id: String? = null,
    @Expose var official_store_id: String? = null,
    @Expose var domain_id: String? = null,
    @Expose var catalog_product_id: String? = null
)

data class Installments(
    @Expose var quantity: Int? = null,
    @Expose var amount: Double? = null,
    @Expose var rate: Int? = null,
    @Expose var currency_id: String? = null
)

data class Attributes(
    @Expose var id: String? = null,
    @Expose var name: String? = null,
    @Expose var value_name: String? = null,
    @Expose var values: List<Values>? = null
)

data class Values(
    @Expose var id: String? = null,
    @Expose var name: String? = null
)
