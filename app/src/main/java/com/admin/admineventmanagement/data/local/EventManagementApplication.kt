package com.admin.admineventmanagement.data.local

import android.app.Application

class EventManagementApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}