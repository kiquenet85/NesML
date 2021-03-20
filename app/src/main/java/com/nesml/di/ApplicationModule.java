package com.nesml.di;

import android.app.Application;

import com.nesml.NesMLApp;

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
}
