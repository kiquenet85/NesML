package com.nesml.storage.model.search.dao

import androidx.room.*
import com.nesml.storage.model.search.entity.Attribute
import kotlinx.coroutines.flow.Flow

@Dao
interface AttributeItemDAO {

    @Query("SELECT * FROM Attribute where id = :id")
    fun getById(id: String): Flow<Attribute?>

    @Query("SELECT * FROM Attribute")
    fun getAll(): Flow<List<Attribute>>

    @Query("SELECT id FROM Attribute where searchItemId = :accountId")
    fun getAllId(accountId: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Attribute>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityToInsert: Attribute)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entityToInsert: Attribute): Int

    @Query("DELETE FROM Attribute")
    suspend fun deleteAll(): Int

    @Query("DELETE FROM Attribute where id = :id")
    suspend fun delete(id: String): Int
}