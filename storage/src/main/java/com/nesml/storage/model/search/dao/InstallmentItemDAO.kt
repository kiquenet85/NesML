package com.nesml.storage.model.search.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nesml.storage.model.search.entity.Installment
import kotlinx.coroutines.flow.Flow

@Dao
interface InstallmentItemDAO {

    @Query("SELECT * FROM Installment where id = :id")
    fun getById(id: String): Flow<Installment?>

    @Query("SELECT * FROM Installment")
    fun getAll(): Flow<List<Installment>>

    @Query("SELECT id FROM Installment where searchItemId = :searchItemId")
    fun getAllId(searchItemId: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Installment>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityToInsert: Installment)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entityToInsert: Installment): Int

    @Query("DELETE FROM Installment")
    suspend fun deleteAll(): Int

    @Query("DELETE FROM Installment where id = :id")
    suspend fun delete(id: String): Int
}