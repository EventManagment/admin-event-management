package com.admin.admineventmanagement.data.repository

import com.admin.admineventmanagement.data.local.datasource.UserEventLocalDataSource
import com.admin.admineventmanagement.data.network.datasource.UserEventRemoteDataSource

class UserEventRepository(
    private val userEventRemoteDataSource: UserEventRemoteDataSource, // network
    private val userEventLocalDataSource: UserEventLocalDataSource // database
) {
//    suspend fun synchronize() {
//        val userData = UserEventRemoteDataSource.fetchUserData()
//        UserEventLocalDataSource.saveUserData(userData)
//    }
}