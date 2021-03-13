package com.nesml.commons.di.application.base;

import android.app.Application;
import android.util.Log;

import com.nesml.commons.di.application.component.BaseAppComponent;
import com.nesml.commons.di.common.component.CommonUIComponent;
import com.nesml.commons.di.common.component.CommonUIProvider;
import com.nesml.commons.di.common.module.CommonUIModule;
import com.nesml.commons.network.NetworkManager;
import com.nesml.commons.network.WifiReceiver;
import com.nesml.commons.settings.Settings;

/**
 * @author n.diazgranados
 */
public abstract class BaseApp extends Application implements CommonUIProvider {

    protected BaseAppComponent baseAppComponent;
    protected CommonUIComponent commonUIComponent;

    /**
     * To get ActivityComponent
     *
     * @return ActivityComponent
     */
    @Override
    public CommonUIComponent getCommonUIComponent() {
        // always get only one instance
        if (commonUIComponent == null) {
            // start lifecycle of chatComponent
            synchronized (this) {
                if (commonUIComponent == null) {
                    Log.d(this.getClass().getSimpleName(), "CREATE CommonUI component");
                    commonUIComponent = baseAppComponent.getCommonUIComponent(new CommonUIModule());
                }
            }
        }
        return commonUIComponent;
    }

    //region BaseAppComponent base methods
    public NetworkManager getNetworkManager() {
        return baseAppComponent.getNetworkManager();
    }

    public WifiReceiver getWifiReceiver() {
        return baseAppComponent.getWifiReceiver();
    }

    public Application getApplication() {
        return baseAppComponent.getApplication();
    }

    public Settings getSettings() {
        return baseAppComponent.getSettings();
    }
    //endregion
}
