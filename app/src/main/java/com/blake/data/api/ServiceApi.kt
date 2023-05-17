package com.blake.data.api

import com.blake.data.dto.ServiceResponse
import retrofit2.http.GET



interface ServiceApi {

    @GET("data")
    suspend fun getData(): ServiceResponse
}