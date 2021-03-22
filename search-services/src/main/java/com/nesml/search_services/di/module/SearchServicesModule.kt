package com.nesml.search_services.di.module

import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.network.NetworkManager
import com.nesml.search_services.api.SearchAPI
import com.nesml.search_services.repository.search.SearchRepository
import com.nesml.search_services.repository.search.sources.SearchLocalSource
import com.nesml.search_services.repository.search.sources.SearchLocalSourceImp
import com.nesml.search_services.repository.search.sources.SearchRemoteSource
import com.nesml.search_services.repository.search.sources.SearchRemoteSourceImp
import dagger.Module
import dagger.Provides

@Module
class SearchServicesModule {

    @Provides
    fun provideSearchRepository(
        searchLocalSource: SearchLocalSource,
        searchRemoteSource: SearchRemoteSource,
        errorHandler: ErrorHandler
    ): SearchRepository =
        SearchRepository(searchLocalSource, searchRemoteSource, errorHandler)

    @Provides
    fun provideSearchLocalSource(searchLocalSourceImp: SearchLocalSourceImp): SearchLocalSource =
        searchLocalSourceImp

    @Provides
    fun provideSearchRemoteSource(searchRemoteSourceImp: SearchRemoteSourceImp): SearchRemoteSource =
        searchRemoteSourceImp

    @Provides
    fun provideSearchAPI(networkManager: NetworkManager): SearchAPI =
        networkManager.defaultRetrofit.create(SearchAPI::class.java)
}