package com.nesml.search_ui.ui.main.di.component;

import com.nesml.search_ui.ui.main.di.module.SearchUIModule;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {SearchUIModule.class})
public interface SearchUIComponent {

}
