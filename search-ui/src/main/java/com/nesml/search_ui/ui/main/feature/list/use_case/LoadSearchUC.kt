package com.nesml.search_ui.ui.main.feature.list.use_case

import com.nesml.commons.util.ACCOUNT_MOCK
import com.nesml.search_services.repository.search.ItemSearchInfo
import com.nesml.search_services.repository.search.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LoadSearchUC(private val searchRepository: SearchRepository) {

    suspend fun execute(query: String) = withContext(Dispatchers.Default) {
        searchRepository.getAll(ItemSearchInfo(ACCOUNT_MOCK, query))
            .map { list ->
                SearchListLoaded(list)
            }
    }
}
