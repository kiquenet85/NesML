package com.nesml.storage.model.search.dao

import androidx.room.*
import com.nesml.storage.model.search.entity.SearchItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchItemDAO {

    @Query("SELECT * FROM SearchItem where id = :id")
    fun getById(id: String): Flow<SearchItem?>

    @Query("SELECT * FROM SearchItem where accountId = :accountId")
    fun getAll(accountId: String): Flow<List<SearchItem>>

    @Query("SELECT id FROM SearchItem where accountId = :accountId")
    fun getAllId(accountId: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<SearchItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityToInsert: SearchItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entityToInsert: SearchItem): Int

    @Query("DELETE FROM SearchItem")
    suspend fun deleteAll(): Int

    @Query("DELETE FROM SearchItem where id = :id")
    suspend fun delete(id: String): Int
}