package com.nesml.di;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nesml.NesMLApp;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies
 *
 * @author n.diazgranados
 */
@Module
public class ApplicationModule {

    private NesMLApp app;

    public ApplicationModule(NesMLApp application) {
        this.app = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return app;
    }

    @Provides
    @Singleton
    @Named("NesMLApp")
    Gson provideGsonObject() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
