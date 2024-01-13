package com.admin.admineventmanagement.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.admin.admineventmanagement.data.local.database.UserEventEntity
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to read/write operations on the schedule table.
 * Used by the view models to format the query results for use in the UI.
 */
@Dao
interface UserEventDao {
    @Query("SELECT * FROM user_event ORDER BY id ASC")
    fun getAll(): Flow<MutableList<UserEventEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userEvent: UserEventEntity)
}