package com.nesml.storage.model.account.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nesml.storage.model.account.entity.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDAO {

    @Query("SELECT * FROM Account where id = :id")
    fun getById(id: String): Flow<Account?>

    @Query("SELECT * FROM Account where id = :accountId")
    fun getAll(accountId: String): Flow<List<android.accounts.Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Account>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entityToInsert: Account)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entityToInsert: Account): Int

    @Query("DELETE FROM Account")
    suspend fun deleteAll(): Int

    @Query("DELETE FROM Account where id = :id")
    suspend fun delete(id: String): Int
}