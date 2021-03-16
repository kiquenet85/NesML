package com.nesml

import android.util.Log
import com.nesml.commons.di.application.base.BaseApp
import com.nesml.commons.di.application.component.BaseAppComponent
import com.nesml.commons.di.application.component.BaseAppComponentProvider
import com.nesml.commons.di.application.component.DaggerBaseAppComponent
import com.nesml.commons.di.application.module.BaseAppModule
import com.nesml.commons.di.application.module.NetworkModule
import com.nesml.di.ApplicationComponent
import com.nesml.di.DaggerApplicationComponent
import com.nesml.di.StorageModule

class NesMLApp : BaseApp(), BaseAppComponentProvider {

    private var applicationComponent: ApplicationComponent? = null

    /**
     * To get ApplicationComponent
     *
     * @return ApplicationComponent
     */
    fun getApplicationComponent(): ApplicationComponent {
        if (applicationComponent == null) {
            Log.d(this.javaClass.simpleName, "CREATE application component")
            applicationComponent = DaggerApplicationComponent.builder()
                .baseAppComponent(getBaseAppComponent())
                .storageModule(StorageModule())
                .build()
        }
        return applicationComponent!!
    }

    override fun getBaseAppComponent(): BaseAppComponent? {
        // always get only one instance
        if (baseAppComponent == null) {
            // start lifecycle of chatComponent
            synchronized(this) {
                baseAppComponent = DaggerBaseAppComponent.builder()
                    .networkModule(NetworkModule())
                    .baseAppModule(BaseAppModule(this))
                    .build()
            }
        }
        return baseAppComponent
    }
}