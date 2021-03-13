package com.nesml.commons.di.application.module;

import android.app.Application;

import com.google.gson.Gson;
import com.nesml.commons.R;
import com.nesml.commons.di.scope.ApplicationContext;
import com.nesml.commons.settings.Settings;
import com.nesml.commons.util.FileUtil;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies
 *
 * @author n.diazgranados
 */
@Module
public class BaseAppModule {

    protected final Application application;
    protected Settings settings;
    protected Gson gson;

    public BaseAppModule(@ApplicationContext Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Settings provideSettings(@ApplicationContext Application applicationContext) {
        if (settings == null) {
            settings = new Gson().fromJson(FileUtil.readFile(applicationContext, R.raw.default_config), Settings.class);
        }
        return settings;
    }

    @Provides
    @ApplicationContext
    Gson provideGsonObject() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
