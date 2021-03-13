package com.nesml.di;

import com.nesml.AnalyticsManager;
import com.nesml.ScrollingActivity;
import com.nesml.commons.di.application.component.BaseAppComponent;

import javax.inject.Singleton;

import dagger.Component;


/**
 * This is the application component running in the application lifecycle {@link com.nesml.commons.di.scope.ApplicationContext}.
 *
 * @author n.diazgranados
 */
@Singleton
@Component(modules = {ApplicationModule.class, AnalyticsModule.class}, dependencies = {BaseAppComponent.class})
public interface ApplicationComponent {

    // Presenter Module
    AnalyticsManager getAnalyticsManager();

    //Views injection
    void inject(ScrollingActivity activity);
}