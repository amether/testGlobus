package com.example.testglobus

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ActivityViewModel @Inject constructor(): BaseViewModel() {
    val simpleLiveData = MutableLiveData<Event<Pets>>()
    fun getUsers(status: String) {
        requestWithLiveData(simpleLiveData) {
            api.getPets(
                status = status
            )
        }
    }

}