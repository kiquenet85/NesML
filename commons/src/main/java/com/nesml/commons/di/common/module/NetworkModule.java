package com.nesml.commons.di.common.module;

import android.app.Application;

import com.nesml.commons.network.NetworkManager;
import com.nesml.commons.network.WifiReceiver;
import com.nesml.commons.settings.Settings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides singleton Network utilities.
 *
 * @author n.diazgranados
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    NetworkManager provideNetworkManager(Application application, Settings settings) {
        return new NetworkManager(application, settings);
    }

    @Provides
    @Singleton
    WifiReceiver provideWifiReceiver(NetworkManager networkManager) {
        return new WifiReceiver(networkManager);
    }
}
