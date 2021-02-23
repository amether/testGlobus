package com.example.testglobus

import androidx.lifecycle.MutableLiveData

class ActivityViewModel : BaseViewModel() {
    // Создаем лайвдату для нашего списка юзеров
    val simpleLiveData = MutableLiveData<Event<Pets>>()

    // Получение юзеров. Обращаемся к функции  requestWithLiveData
    // из BaseViewModel передаем нашу лайвдату и говорим,
    // какой сетевой запрос нужно выполнить и с какими параметрами
    // В данном случае это api.getUsers
    // Теперь функция сама выполнит запрос и засетит нужные
    // данные в лайвдату
    fun getUsers(status: String) {
        requestWithLiveData(simpleLiveData) {
            api.getPets(
                status = status
            )
        }
    }

}