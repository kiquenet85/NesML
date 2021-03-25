package com.nesml.search_ui.ui.main.di.module

import androidx.savedstate.SavedStateRegistryOwner
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.commons.network.NetworkManager
import com.nesml.search_services.repository.search.SearchRepository
import com.nesml.search_ui.ui.main.feature.list.ui.SearchViewModelFactory

class SearchViewModelModule(
    private val resourceManager: ResourceManager,
    private val errorHandler: ErrorHandler,
    private val searchRepository: SearchRepository,
    private val networkManager: NetworkManager
) {

    fun provideSearchViewModelFactory(owner: SavedStateRegistryOwner) =
        SearchViewModelFactory(
            owner,
            errorHandler,
            resourceManager,
            searchRepository,
            networkManager
        )
}