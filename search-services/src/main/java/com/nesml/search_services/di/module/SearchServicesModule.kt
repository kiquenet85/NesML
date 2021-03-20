package com.nesml.search_services.di.module

import androidx.room.RoomDatabase
import com.nesml.commons.network.NetworkManager
import com.nesml.search_services.api.SearchAPI
import com.nesml.search_services.repository.search.*
import dagger.Module

@Module
class SearchServicesModule {

    fun provideSearchRepository(
        db: RoomDatabase,
        searchLocalSource: SearchLocalSource,
        searchRemoteSource: SearchRemoteSource
    ): SearchRepository =
        SearchRepository(searchLocalSource, searchRemoteSource)

    fun provideSearchLocalSource(db: RoomDatabase): SearchLocalSource = SearchLocalSourceImp(db)

    fun provideSearchRemoteSource(searchAPI: SearchAPI): SearchRemoteSource =
        SearchRemoteSourceImp(searchAPI)

    fun provideSearchAPI(networkManager: NetworkManager): SearchAPI =
        networkManager.defaultRetrofit.create(SearchAPI::class.java)
}