package com.nesml.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nesml.storage.model.account.dao.AccountDAO
import com.nesml.storage.model.account.entity.Account
import com.nesml.storage.model.search.dao.AttributeItemDAO
import com.nesml.storage.model.search.dao.AttributeValueDAO
import com.nesml.storage.model.search.dao.InstallmentItemDAO
import com.nesml.storage.model.search.dao.SearchItemDAO
import com.nesml.storage.model.search.entity.Attribute
import com.nesml.storage.model.search.entity.AttributeValue
import com.nesml.storage.model.search.entity.Installment
import com.nesml.storage.model.search.entity.SearchItem

@Database(
    entities = [
        Account::class, SearchItem::class, Attribute::class, Installment::class, AttributeValue::class
    ], version = 3
)
abstract class AppDB : RoomDatabase() {

    abstract fun accountDAO(): AccountDAO
    abstract fun searchItemDAO(): SearchItemDAO
    abstract fun attributeItemDAO(): AttributeItemDAO
    abstract fun attributeValueDAO(): AttributeValueDAO
    abstract fun installmentItemDAO(): InstallmentItemDAO
}