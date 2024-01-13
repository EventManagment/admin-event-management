package com.admin.admineventmanagement.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admin.admineventmanagement.data.models.AdminUser
import com.admin.admineventmanagement.data.network.AdminApi
import kotlinx.coroutines.launch

class UserAccountViewModel : ViewModel() {
    private var _adminUser = MutableLiveData<AdminUser>()
    val listUserEvent: LiveData<AdminUser>
        get() = _adminUser

    init {
        getAdminData()
    }
    private fun getAdminData() {
        try {
            // some code that can cause an exception.
            viewModelScope.launch {
                val listResult = AdminApi.retrofitService.getAdminUser()
                _adminUser.value = listResult
                Log.d("ViewModel", "\"Success: ${listResult} Users photos retrieved\"\n")
            }
        }
        catch (e: Exception) {
            // handle the exception to avoid abrupt termination.
        }
    }
}