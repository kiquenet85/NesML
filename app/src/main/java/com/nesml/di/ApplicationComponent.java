package com.nesml.di;

import com.google.gson.Gson;
import com.nesml.MainActivity;
import com.nesml.commons.di.common.component.CommonComponent;
import com.nesml.commons.di.common.module.CommonModule;
import com.nesml.commons.di.common.module.NetworkModule;
import com.nesml.search_services.di.component.SearchServicesComponent;
import com.nesml.search_services.di.module.SearchServicesModule;
import com.nesml.search_ui.ui.main.di.component.SearchUIComponent;
import com.nesml.search_ui.ui.main.di.module.SearchUIModule;
import com.nesml.storage.di.component.StorageComponent;
import com.nesml.storage.di.module.StorageModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 * This is the application component running in the application lifecycle.
 *
 * @author n.diazgranados
 */
@Singleton
@Component(modules = {ApplicationModule.class, StorageModule.class, CommonModule.class, NetworkModule.class, SearchServicesModule.class, SearchUIModule.class})
public interface ApplicationComponent {

    //Views injection
    void inject(MainActivity activity);

    //Common SubComponent
    CommonComponent getCommonComponent();

    //SearchServices SubComponent
    SearchUIComponent getSearchUIComponent();

    //SearchServices SubComponent
    SearchServicesComponent getSearchServicesComponent();

    //Storage SubComponent
    StorageComponent getStorageComponent();

    @Named("NesMLApp")
    Gson getGson();
}