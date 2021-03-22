package com.nesml.storage.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nesml.storage.di.model.search.dao.SearchItemDAO
import com.nesml.storage.di.model.search.entity.SearchItem

@Database(
    entities = [
        SearchItem::class
    ], version = 1
)
abstract class AppDB : RoomDatabase() {

    abstract fun searchItemDAO(): SearchItemDAO
}