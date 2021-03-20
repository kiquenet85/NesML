package com.nesml.search_services.model.network

import com.google.gson.annotations.Expose

data class SearchItemDTO(
    @Expose val id: String,
    @Expose val site_id: String? = null,
    @Expose val title: String? = null,
    @Expose val price: Int? = null,
    @Expose val sale_price: String? = null,
    @Expose val currency_id: String? = null,
    @Expose val available_quantity: Int? = null,
    @Expose val sold_quantity: Int? = null,
    @Expose val buying_mode: String? = null,
    @Expose val listing_type_id: String? = null,
    @Expose val stop_time: String? = null,
    @Expose val condition: String? = null,
    @Expose val permalink: String? = null,
    @Expose val thumbnail: String? = null,
    @Expose val thumbnail_id: String? = null,
    @Expose val accepts_mercadopago: Boolean? = null,
    @Expose val installments: Installments? = null,
    @Expose val attributes: List<Attributes>? = null,
    @Expose val original_price: Int? = null,
    @Expose val category_id: String? = null,
    @Expose val official_store_id: String? = null,
    @Expose val domain_id: String? = null,
    @Expose val catalog_product_id: String? = null
)

data class Installments(
    @Expose val quantity: Int? = null,
    @Expose val amount: Int? = null,
    @Expose val rate: Int? = null,
    @Expose val currency_id: String? = null
)

data class Attributes(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val value_name: String? = null,
    @Expose val values: List<Values>? = null
)

data class Values(
    @Expose val id: String? = null,
    @Expose val name: String? = null
)
