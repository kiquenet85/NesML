package com.nesml.di;

import com.nesml.ScrollingActivity;
import com.nesml.commons.di.application.component.BaseAppComponent;
import com.nesml.db.AppDB;

import javax.inject.Singleton;

import dagger.Component;


/**
 * This is the application component running in the application lifecycle {@link com.nesml.commons.di.scope.ApplicationContext}.
 *
 * @author n.diazgranados
 */
@Singleton
@Component(modules = {ApplicationModule.class, StorageModule.class}, dependencies = {BaseAppComponent.class})
public interface ApplicationComponent {

    // Storage Module
    AppDB getDB();

    //Views injection
    void inject(ScrollingActivity activity);
}