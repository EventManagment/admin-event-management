package com.admin.admineventmanagement.data.network.models

/**
 * Network representation of [NetworkUserEvent]
 */
data class NetworkUserEvent (
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
)