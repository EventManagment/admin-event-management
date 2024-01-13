package com.admin.admineventmanagement.data.local.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single table in the database. Each row is a separate instance of the Schedule class.
 * Each property corresponds to a column. Additionally, an ID is needed as a unique identifier for
 * each row in the database.
 */
@Entity(tableName="user_event")
data class UserEventEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "username") val name: String,
    @NonNull @ColumnInfo(name = "email") val email: String,
    @NonNull @ColumnInfo(name = "phone") val phone: String,
)