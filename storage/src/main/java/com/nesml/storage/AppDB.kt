package com.nesml.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nesml.storage.model.search.dao.SearchItemDAO
import com.nesml.storage.model.search.entity.Attributes
import com.nesml.storage.model.search.entity.Installments
import com.nesml.storage.model.search.entity.SearchItem
import com.nesml.storage.model.search.entity.Values

@Database(
        entities = [
            SearchItem::class, Attributes::class, Installments::class, Values::class
        ], version = 1
)
abstract class AppDB : RoomDatabase() {

    abstract fun searchItemDAO(): SearchItemDAO
}