package com.nesml.search_ui.ui.main.feature.list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nesml.commons.BaseCoroutineViewModel
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.search_ui.ui.main.feature.list.use_case.LoadSearchUC
import com.nesml.search_ui.ui.main.feature.list.use_case.SearchListState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchListViewModel(
    private val state: SavedStateHandle,
    resourceManager: ResourceManager,
    errorHandler: ErrorHandler,
    private val loadSearchUC: LoadSearchUC,
) : BaseCoroutineViewModel(resourceManager, errorHandler) {

    private val stateScreen = MutableLiveData<SearchListState>()
    fun getScreenState(): LiveData<SearchListState> = stateScreen

    fun searchItemResults(query: String) {
        viewModelScope.launch(errorHandler) {
            loadSearchUC.execute(query).collect {
                stateScreen.value = it
            }
        }
    }
}

