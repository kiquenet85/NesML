package com.nesml.search_ui.ui.main.feature.list.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.commons.network.NetworkManager
import com.nesml.search_services.repository.search.SearchRepository
import com.nesml.search_ui.ui.main.feature.detail.use_case.LoadSearchItemUC
import com.nesml.search_ui.ui.main.feature.list.use_case.LoadSearchItemListUC

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory constructor(
    owner: SavedStateRegistryOwner,
    private val errorHandler: ErrorHandler,
    private val resourceManager: ResourceManager,
    private val searchRepository: SearchRepository,
    private val networkManager: NetworkManager,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return with(modelClass) {
            when {
                isAssignableFrom(SearchListViewModel::class.java) ->
                    SearchListViewModel(
                        handle,
                        resourceManager,
                        errorHandler,
                        networkManager,
                        LoadSearchItemListUC(searchRepository),
                        LoadSearchItemUC(searchRepository)
                    )

                else -> throw IllegalArgumentException("Unknown ViewModel class in Locations: ${modelClass.name}")
            }
        } as T
    }
}