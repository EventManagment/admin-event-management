package com.admin.admineventmanagement.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admin.admineventmanagement.data.models.UserEvent
import com.admin.admineventmanagement.data.network.UserEventApi
import kotlinx.coroutines.launch

class ManagementViewModel : ViewModel() {
    private var _listUserEvent = MutableLiveData<MutableList<UserEvent>>()
    val listUserEvent: LiveData<MutableList<UserEvent>>
        get() = _listUserEvent

    private var _currentUserEvent = MutableLiveData<MutableList<UserEvent>>()
    val currentUserEvent: LiveData<MutableList<UserEvent>>
        get() = _currentUserEvent

    private var _userJoin = MutableLiveData<MutableList<UserEvent>>()
     val userJoin: LiveData<MutableList<UserEvent>>
        get() = _userJoin

    private var _userAbsent = MutableLiveData<MutableList<UserEvent>>()
    val userAbsent: LiveData<MutableList<UserEvent>>
        get() = _userAbsent

    private var _userJoinAndRegister = MutableLiveData<MutableList<UserEvent>>()
     val userJoinAndRegister: LiveData<MutableList<UserEvent>>
        get() = _userJoinAndRegister

    init {
        getEventUserData()
//        data()
    }

//    private fun data() {
//        if (listUserEvent.value?.size!! > 0) {
//            for (user in listUserEvent.value!!) {
//                if (user.id <= 4.toString()) userJoin.value?.add(user)
//                if ((user.id > 4.toString()) && (user.id <= 7.toString())) userAbsent.value?.add(user)
//                if (user.id > 7.toString()) userJoinAndRegister.value?.add(user)
//            }
//        }
//    }


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

//    fun getAllUserEvent(): Flow<MutableList<UserEventDB>> = userEventDao.getAll()
}

//class UserEventViewModelFactory(
//    private val userEventDao: UserEventDao
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ManagementViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ManagementViewModel(userEventDao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}