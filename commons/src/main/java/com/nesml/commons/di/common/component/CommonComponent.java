package com.nesml.commons.di.common.component;

import com.google.gson.Gson;
import com.nesml.commons.di.common.module.CommonModule;
import com.nesml.commons.di.common.module.NetworkModule;
import com.nesml.commons.error.ErrorHandler;
import com.nesml.commons.manager.ResourceManager;
import com.nesml.commons.network.NetworkManager;
import com.nesml.commons.network.WifiReceiver;
import com.nesml.commons.settings.Settings;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities in the activity lifecycle
 *
 * @author n.diazgranados.
 */
@Singleton
@Subcomponent(modules = {CommonModule.class, NetworkModule.class})
public interface CommonComponent {

    //Network module
    NetworkManager getNetworkManager();

    WifiReceiver getWifiReceiver();

    Settings getSettings();

    ResourceManager getResourceManager();

    @Named("NesMLApp")
    Gson getGson();

    ErrorHandler getErrorHandler();
}
