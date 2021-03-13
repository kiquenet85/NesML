package com.nesml.commons.di.controller.module;

import android.app.Activity;

import com.nesml.commons.di.scope.ActivityScope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dagger.Module;

/**
 * @author n.diazgranados
 */
@Module
public class CacheModule {

    //TODO verify if this type of cache is necessary, or using local DB is enough.
    Map<String, Object> baseLocalCache;

    Map<String, Object> provideControllerCache(@ActivityScope Activity activity) {
        if (baseLocalCache == null) {
            baseLocalCache = new ConcurrentHashMap<>();
        }
        ;
        return baseLocalCache;
    }
}
