package com.nesml.search_ui.ui.main.feature.list.use_case

import com.nesml.storage.model.search.entity.SearchItem

sealed class SearchListState

open class SearchListLoaded(
    val allItems: List<SearchItem>,
) : SearchListState()