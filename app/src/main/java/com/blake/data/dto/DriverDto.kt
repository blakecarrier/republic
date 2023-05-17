package com.blake.data.dto

import com.google.gson.annotations.SerializedName

data class DriverDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
){
    val lastName : String
        get() = name.split(" ").last()
}
