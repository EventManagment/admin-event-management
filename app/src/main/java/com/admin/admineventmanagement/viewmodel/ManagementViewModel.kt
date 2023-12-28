package com.admin.admineventmanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.admin.admineventmanagement.model.UserEvent

class ManagementViewModel : ViewModel() {
    private var _listUserEvent = MutableLiveData<MutableList<UserEvent>>()
    val listUserEvent: LiveData<MutableList<UserEvent>>
        get() = _listUserEvent

    private var _currentUserEvent = MutableLiveData<MutableList<UserEvent>>()
    val currentUserEvent: LiveData<MutableList<UserEvent>>
        get() = _currentUserEvent

}