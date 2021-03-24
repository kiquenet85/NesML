package com.nesml.search_ui.ui.main.di.module

import com.nesml.commons.error.ErrorHandler
import com.nesml.search_services.repository.search.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SearchUIModule {

    @Provides
    @Singleton
    fun provideSearchViewModelModule(
        errorHandler: ErrorHandler,
        searchRepository: SearchRepository
    ) =
        SearchViewModelModule(errorHandler, searchRepository)
}