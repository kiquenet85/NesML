package com.nesml.di

import android.app.Application
import androidx.room.Room
import com.nesml.db.AppDB
import dagger.Module
import dagger.Provides

/**
 * @author n.diazgranados
 */
@Module
class StorageModule {

    //LoginActivity
    @Provides
    fun provideDB(context: Application): AppDB =
        Room.databaseBuilder(context, AppDB::class.java, "AppDB")
            .fallbackToDestructiveMigration().build()

}
