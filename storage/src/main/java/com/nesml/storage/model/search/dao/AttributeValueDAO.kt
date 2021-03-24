package com.nesml.storage.model.search.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nesml.storage.model.search.entity.AttributeValue
import kotlinx.coroutines.flow.Flow

@Dao
interface AttributeValueDAO {

    @Query("SELECT * FROM AttributeValue where id = :id")
    fun getById(id: String): Flow<AttributeValue?>

    @Query("SELECT * FROM AttributeValue")
    fun getAll(): Flow<List<AttributeValue>>

    @Query("SELECT id FROM AttributeValue where attributeId = :attributeId")
    fun getAllId(attributeId: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<AttributeValue>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityToInsert: AttributeValue)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entityToInsert: AttributeValue): Int

    @Query("DELETE FROM AttributeValue")
    suspend fun deleteAll(): Int

    @Query("DELETE FROM AttributeValue where id = :id")
    suspend fun delete(id: String): Int
}