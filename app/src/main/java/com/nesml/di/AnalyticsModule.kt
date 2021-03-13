package com.nesml.di

import com.nesml.AnalyticsManager
import dagger.Module
import dagger.Provides

/**
 * @author n.diazgranados
 */
@Module
class AnalyticsModule {

    //LoginActivity
    @Provides
    internal fun provideAnalyticsManager(): AnalyticsManager = AnalyticsManager()

}
