package com.example.composemvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composemvvm.data.models.roommodel.SampleTable
import com.example.composemvvm.data.models.roommodel.UserTable

@Database(
    entities = [UserTable::class, SampleTable::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun sampleDao(): SampleDao
}