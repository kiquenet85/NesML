package com.nesml.commons.di.controller.component;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.nesml.commons.BaseActivity;
import com.nesml.commons.BaseFragment;
import com.nesml.commons.di.controller.module.ActivityModule;
import com.nesml.commons.di.controller.module.CacheModule;
import com.nesml.commons.di.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities in the activity lifecycle {@link ActivityScope}
 *
 * @author n.diazgranados.
 */

@ActivityScope
@Subcomponent(modules = {ActivityModule.class, CacheModule.class})
public interface ActivityComponent {

    //Views
    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    @ActivityScope
    Activity getActivity();

    @ActivityScope
    Context getContext();

    @ActivityScope
    Context getLocalCache();

    @ActivityScope
    Handler getMainThreadHandler();
}
