package com.nesml

import android.app.Application
import com.nesml.commons.di.common.component.CommonComponent
import com.nesml.commons.di.common.component.CommonProvider
import com.nesml.di.ApplicationComponent
import com.nesml.di.ApplicationModule
import com.nesml.di.DaggerApplicationComponent
import com.nesml.search_services.di.component.SearchServicesComponent
import com.nesml.search_services.di.component.SearchServicesProvider

class NesMLApp : Application(), CommonProvider, SearchServicesProvider {

    private var applicationComponent: ApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

    private var searchServicesComponent: SearchServicesComponent =
        applicationComponent.searchServicesComponent

    private var commonComponent: CommonComponent = applicationComponent.commonComponent

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }

    override fun getSearchServices(): SearchServicesComponent {
        return searchServicesComponent
    }

    override fun getCommonComponent(): CommonComponent {
        return commonComponent
    }
}