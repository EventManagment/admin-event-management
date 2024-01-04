package com.admin.admineventmanagement.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admin.admineventmanagement.model.UserEvent
import com.admin.admineventmanagement.network.UserEventApi
import kotlinx.coroutines.launch

class ManagementViewModel : ViewModel() {
    private var _listUserEvent = MutableLiveData<MutableList<UserEvent>>()
    val listUserEvent: LiveData<MutableList<UserEvent>>
        get() = _listUserEvent

    private var _currentUserEvent = MutableLiveData<MutableList<UserEvent>>()
    val currentUserEvent: LiveData<MutableList<UserEvent>>
        get() = _currentUserEvent

    init {
        getEventUserData()
    }

    private fun getEventUserData() {
        try {
            // some code that can cause an exception.
            viewModelScope.launch {
                val listResult = UserEventApi.retrofitService.getEventUser()
                _listUserEvent.value = listResult
                Log.d("ViewModel", "\"Success: ${listResult.size} Users photos retrieved\"\n")
            }
        }
        catch (e: Exception) {
            // handle the exception to avoid abrupt termination.
        }

    }
}