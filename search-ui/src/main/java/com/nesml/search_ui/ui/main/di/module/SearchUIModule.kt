package com.nesml.search_ui.ui.main.di.module

import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.search_services.repository.search.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SearchUIModule {

    @Provides
    @Singleton
    fun provideSearchViewModelModule(
        resourceManager: ResourceManager,
        errorHandler: ErrorHandler,
        searchRepository: SearchRepository
    ) =
        SearchViewModelModule(resourceManager, errorHandler, searchRepository)
}