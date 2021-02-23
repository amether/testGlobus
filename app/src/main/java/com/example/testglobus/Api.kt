package com.example.testglobus

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/#/pet/findPetsByStatus")
    suspend fun getPets(@Query("status") status: String): ResponseWrapper<Pets>
}