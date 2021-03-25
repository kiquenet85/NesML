package com.nesml.search_ui.ui.main.feature.detail.use_case

import com.nesml.storage.model.search.entity.SearchItem

sealed class SearchDetailState

open class SearchDetailLoaded(
    val item: SearchItem,
) : SearchDetailState()