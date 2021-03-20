package com.nesml.search_services.di.component;

import com.nesml.search_services.di.module.SearchServicesModule;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {SearchServicesModule.class})
public interface SearchServicesComponent {

}
