package com.blake.data.dto

import com.google.gson.annotations.SerializedName

data class ServiceResponse(
    @SerializedName("drivers")
    val drivers: List<DriverDto>,
    @SerializedName("routes")
    val routes: List<RouteDto>
)

