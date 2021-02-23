package com.example.testglobus

import dagger.Component

@Component
interface DaggerComponent {
    fun getActivityViewModel(): ActivityViewModel
}