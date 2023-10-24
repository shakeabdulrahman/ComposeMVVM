package com.example.composemvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composemvvm.data.models.roommodel.UserTable

@Dao
interface SampleDao {
    @Query("SELECT * FROM SAMPLE_TABLE")
    fun getAllUsers(): LiveData<List<UserTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserTable>)
}