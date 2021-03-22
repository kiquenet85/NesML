package com.nesml.storage.di.component;

import com.nesml.storage.AppDB;
import com.nesml.storage.di.module.StorageModule;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {StorageModule.class})
public interface StorageComponent {

    // Storage Module
    AppDB getDB();
}
