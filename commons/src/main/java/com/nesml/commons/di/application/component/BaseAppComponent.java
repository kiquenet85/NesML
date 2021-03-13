package com.nesml.commons.di.application.component;

import android.app.Application;

import com.google.gson.Gson;
import com.nesml.commons.di.application.module.BaseAppModule;
import com.nesml.commons.di.application.module.NetworkModule;
import com.nesml.commons.di.common.component.CommonUIComponent;
import com.nesml.commons.di.common.module.CommonUIModule;
import com.nesml.commons.di.scope.ApplicationContext;
import com.nesml.commons.network.NetworkManager;
import com.nesml.commons.network.WifiReceiver;
import com.nesml.commons.settings.Settings;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This is the application component running in the application lifecycle {@link ApplicationContext}.
 *
 * @author n.diazgranados
 */
@Component(modules = {BaseAppModule.class, NetworkModule.class})
public interface BaseAppComponent {

    //Views injection
    void inject(WifiReceiver wifiReceiver);

    //Subcomponent
    @Singleton
    CommonUIComponent getCommonUIComponent(CommonUIModule commonUIModule);

    //Network module
    @ApplicationContext
    NetworkManager getNetworkManager();

    @ApplicationContext
    WifiReceiver getWifiReceiver();

    //Application component
    @ApplicationContext
    Application getApplication();

    @ApplicationContext
    Settings getSettings();

    @ApplicationContext
    Gson getGson();

    //Presenter Component
    /*PlansPresenter getConverterPresenter();
    FetchAllPlansInteractor getFetchAllCurrenciesInteractor();*/

    //Storage
    //PlansLocalCache getConverterLocalCache();

    //Others
    /*PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    CompositeSubscription compositeSubscription();*/
}
