package com.nesml.search_services.di.component;

import com.nesml.search_services.api.SearchAPI;
import com.nesml.search_services.di.module.SearchServicesModule;
import com.nesml.search_services.repository.search.SearchRepository;
import com.nesml.search_services.repository.search.sources.SearchLocalSource;
import com.nesml.search_services.repository.search.sources.SearchRemoteSource;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {SearchServicesModule.class})
public interface SearchServicesComponent {
    SearchRepository getSearchRepository();

    SearchLocalSource getSearchLocalSource();

    SearchRemoteSource getSearchRemoteSource();

    SearchAPI getSearchAPI();
}
