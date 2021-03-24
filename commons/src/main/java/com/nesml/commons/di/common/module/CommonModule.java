package com.nesml.commons.di.common.module;

import android.app.Application;

import com.google.gson.Gson;
import com.nesml.commons.R;
import com.nesml.commons.error.ErrorHandler;
import com.nesml.commons.manager.ResourceManager;
import com.nesml.commons.settings.Settings;
import com.nesml.commons.util.FileUtil;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides activity context.
 *
 * @author n.diazgranados
 */
@Module
public class CommonModule {

    @Provides
    @Singleton
    Settings provideSettings(Application applicationContext, @Named("NesMLApp") Gson gson) {
        return gson.fromJson(FileUtil.readFile(applicationContext, R.raw.default_config), Settings.class);
    }

    @Provides
    @Singleton
    ErrorHandler provideErrorHandler() {
        return new ErrorHandler();
    }

    @Provides
    @Singleton
    ResourceManager provideResourceManager(Application applicationContext) {
        return ResourceManager.getInstance(applicationContext);
    }
}
