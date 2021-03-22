package com.nesml.storage.di.module

import android.app.Application
import androidx.room.Room
import com.nesml.storage.di.AppDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun providesApplication(application: Application): AppDB {
        return Room.databaseBuilder(application.applicationContext, AppDB::class.java, "RoomDB")
            .fallbackToDestructiveMigration().build()
    }
}