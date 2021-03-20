package com.nesml.di;

import com.nesml.MainActivity;
import com.nesml.commons.di.common.component.CommonComponent;
import com.nesml.commons.di.common.module.CommonModule;
import com.nesml.commons.di.common.module.NetworkModule;
import com.nesml.db.AppDB;
import com.nesml.search_services.di.component.SearchServicesComponent;
import com.nesml.search_services.di.module.SearchServicesModule;
import com.nesml.search_ui.ui.main.di.module.SearchUIModule;

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
    SearchServicesComponent getSearchServicesComponent();

    // Storage Module
    AppDB getDB();
}