package com.nesml.search_ui.ui.main.feature.list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nesml.commons.BaseCoroutineViewModel
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.search_ui.ui.main.feature.detail.use_case.LoadSearchItemUC
import com.nesml.search_ui.ui.main.feature.detail.use_case.SearchDetailState
import com.nesml.search_ui.ui.main.feature.list.use_case.LoadSearchItemListUC
import com.nesml.search_ui.ui.main.feature.list.use_case.SearchListState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchListViewModel(
    private val state: SavedStateHandle,
    resourceManager: ResourceManager,
    errorHandler: ErrorHandler,
    private val loadSearchItemListUC: LoadSearchItemListUC,
    private val loadSearchItemUC: LoadSearchItemUC
) : BaseCoroutineViewModel(resourceManager, errorHandler) {

    private val stateListScreen = MutableLiveData<SearchListState>()
    fun getScreenListState(): LiveData<SearchListState> = stateListScreen

    private val stateDetailScreen = MutableLiveData<SearchDetailState>()
    fun getScreenDetailState(): LiveData<SearchDetailState> = stateDetailScreen

    fun searchItemResults(query: String) {
        viewModelScope.launch(errorHandler) {
            loadSearchItemListUC.execute(query).collect {
                stateListScreen.value = it
            }
        }
    }

    fun getSearchItem(searchItemId: String) {
        viewModelScope.launch(errorHandler) {
            loadSearchItemUC.execute(searchItemId).collect {
                stateDetailScreen.value = it
            }
        }
    }
}

