package com.example.testglobus

import com.google.gson.annotations.SerializedName

data class Pets(
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status: String)
