package com.nesml.commons.di.controller.module;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.nesml.commons.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides activity context.
 *
 * @author n.diazgranados
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    Handler provideMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
