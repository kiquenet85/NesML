package com.nesml

import android.app.Application
import com.google.gson.Gson
import com.nesml.commons.di.common.component.CommonComponent
import com.nesml.commons.di.common.component.CommonProvider
import com.nesml.commons.parser.GsonProvider
import com.nesml.di.ApplicationComponent
import com.nesml.di.ApplicationModule
import com.nesml.di.DaggerApplicationComponent
import com.nesml.search_services.di.component.SearchServicesComponent
import com.nesml.search_services.di.component.SearchServicesProvider
import com.nesml.search_ui.ui.main.di.component.SearchUIComponent
import com.nesml.search_ui.ui.main.di.component.SearchUIProvider
import com.nesml.storage.di.component.StorageComponent
import com.nesml.storage.di.component.StorageComponentProvider

class NesMLApp : Application(), GsonProvider, CommonProvider, SearchUIProvider,
    SearchServicesProvider,
    StorageComponentProvider {

    private var applicationComponent: ApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

    private var searchUIComponent: SearchUIComponent =
        applicationComponent.searchUIComponent

    private var searchServicesComponent: SearchServicesComponent =
        applicationComponent.searchServicesComponent

    private var commonComponent: CommonComponent = applicationComponent.commonComponent

    private var storagecomponent: StorageComponent = applicationComponent.storageComponent

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }

    override fun getSearchServices(): SearchServicesComponent {
        return searchServicesComponent
    }

    override fun getCommonComponent(): CommonComponent {
        return commonComponent
    }

    override fun getDatabase(): StorageComponent {
        return storagecomponent
    }

    override fun getGson(): Gson {
        return applicationComponent.gson
    }

    override fun getSearchUI(): SearchUIComponent {
        return searchUIComponent
    }
}