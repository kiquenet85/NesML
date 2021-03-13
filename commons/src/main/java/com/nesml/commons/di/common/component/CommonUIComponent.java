package com.nesml.commons.di.common.component;

import com.nesml.commons.di.common.module.CommonUIModule;
import com.nesml.commons.di.controller.component.ActivityComponent;
import com.nesml.commons.di.controller.module.ActivityModule;
import com.nesml.commons.di.scope.ActivityScope;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities in the activity lifecycle {@link ActivityScope}
 *
 * @author n.diazgranados.
 */

@Singleton
@Subcomponent(modules = {CommonUIModule.class})
public interface CommonUIComponent {

    //Subcomponent
    @ActivityScope
    ActivityComponent getActivityComponent(ActivityModule activityModule);

}
