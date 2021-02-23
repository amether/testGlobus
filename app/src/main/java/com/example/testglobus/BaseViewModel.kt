package com.example.testglobus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    var api: Api = NetworkService.retrofitService()

    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> ResponseWrapper<T>) {
        liveData.postValue(Event.loading())
        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                if (response.data != null) {
                    // Сетим в лайвдату командой postValue в IO потоке
                    liveData.postValue(Event.success(response.data))
                } else if (response.error != null) {
                    liveData.postValue(Event.error(response.error))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }
}