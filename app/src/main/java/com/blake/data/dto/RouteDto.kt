package com.blake.data.dto

import com.google.gson.annotations.SerializedName

data class RouteDto(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("name")
    val name: String = "",
) {
    val isRType: Boolean
        get() = type == "R"
    val isCType: Boolean
        get() = type == "C"
    val isIType: Boolean
        get() = type == "I"
}

