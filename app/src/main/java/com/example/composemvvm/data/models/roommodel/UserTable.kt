package com.example.composemvvm.data.models.roommodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composemvvm.data.models.roommodel.TableNames.USERS_TABLE

@Entity(tableName = USERS_TABLE)
data class UserTable(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "avatar") val avatar: String
)