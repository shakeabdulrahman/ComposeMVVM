package com.example.composemvvm.data.models.roommodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composemvvm.data.models.roommodel.TableNames.SAMPLE_TABLE

@Entity(tableName = SAMPLE_TABLE)
data class SampleTable(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "avatar") val avatar: String
)