package com.nesml.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nesml.search_services.model.db.SearchModuleDB
import com.nesml.search_services.model.db.dao.SearchItemDAO
import com.nesml.search_services.model.db.entity.SearchItem

@Database(
    entities = [
        SearchItem::class
    ], version = 1
)
abstract class AppDB : RoomDatabase(), SearchModuleDB {

    abstract override fun searchItemDAO(): SearchItemDAO
}