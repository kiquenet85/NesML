package com.nesml.search_ui.ui.main.di.module

import androidx.savedstate.SavedStateRegistryOwner
import com.nesml.commons.error.ErrorHandler
import com.nesml.search_services.repository.search.SearchRepository
import com.nesml.search_ui.ui.main.feature.list.ui.SearchViewModelFactory

class SearchViewModelModule(
    private val errorHandler: ErrorHandler,
    private val searchRepository: SearchRepository
) {

    fun provideSearchViewModelFactory(owner: SavedStateRegistryOwner) =
        SearchViewModelFactory(owner, errorHandler, searchRepository)
}