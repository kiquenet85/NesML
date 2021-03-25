package com.nesml.search_ui.ui.main.feature.detail.use_case

import com.nesml.search_services.repository.search.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LoadSearchItemUC(private val searchRepository: SearchRepository) {

    suspend fun execute(searchItemId: String) = withContext(Dispatchers.Default) {
        searchRepository.getById(searchItemId)
            .map { item ->
                SearchDetailLoaded(item)
            }
    }
}
