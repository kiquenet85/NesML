package com.nesml.search_services.di.module

import androidx.room.RoomDatabase
import com.nesml.search_services.repository.search.SearchLocalSource
import com.nesml.search_services.repository.search.SearchLocalSourceImp
import com.nesml.search_services.repository.search.SearchRepository
import dagger.Module

@Module
class SearchServicesModule {

    fun provideSearchRepository(db: RoomDatabase): SearchRepository =
        SearchRepository(provideSearchLocalSource(db))

    fun provideSearchLocalSource(db: RoomDatabase): SearchLocalSource = SearchLocalSourceImp(db)
}