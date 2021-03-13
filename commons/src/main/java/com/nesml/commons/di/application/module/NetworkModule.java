package com.nesml.commons.di.application.module;

import android.app.Application;

import com.nesml.commons.di.scope.ApplicationContext;
import com.nesml.commons.network.NetworkManager;
import com.nesml.commons.network.WifiReceiver;
import com.nesml.commons.settings.Settings;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides singleton Network utilities.
 *
 * @author n.diazgranados
 */
@Module
public class NetworkModule {

    protected NetworkManager networkManager;
    protected WifiReceiver wifiReceiver;

    @Provides
    @ApplicationContext
    NetworkManager provideNetworkManager(@ApplicationContext Application application, @ApplicationContext Settings settings) {
        if (networkManager == null) {
            networkManager = new NetworkManager(application, settings);
        }
        return networkManager;
    }

    @Provides
    @ApplicationContext
    WifiReceiver provideWifiReceiver(@ApplicationContext NetworkManager networkManager) {
        if (wifiReceiver == null) {
            wifiReceiver = new WifiReceiver(networkManager);
        }
        return wifiReceiver;
    }
}
