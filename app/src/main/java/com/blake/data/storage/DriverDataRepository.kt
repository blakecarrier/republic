package com.blake.data.storage

import com.blake.data.api.ServiceApi
import com.blake.data.dto.DriverDto
import com.blake.data.dto.RouteDto
import java.util.Collections
import javax.inject.Inject

interface DriverDataRepository {
    suspend fun getDrivers(): List<DriverDto>
    suspend fun getRoutes(): List<RouteDto>
}

class DriverDataRepositoryImpl @Inject constructor(
    private val storage: DriverDataStorage,
    private val api: ServiceApi
) : DriverDataRepository {

    override suspend fun getDrivers(): List<DriverDto> {
        val localDriverData = storage.getDrivers()
        if (localDriverData.isEmpty()) {
            val response = api.getData()
            storage.saveDrivers(response.drivers)
            storage.saveRoutes(response.routes)
        }
        return storage.getDrivers()
    }

    override suspend fun getRoutes(): List<RouteDto> =
        try {
            val localRouteData = storage.getRoutes()
            if (localRouteData.isEmpty()) {
                val response = api.getData()
                storage.saveDrivers(response.drivers)
                storage.saveRoutes(response.routes)
            }
            storage.getRoutes()

        } catch (e: Exception) {
            Collections.emptyList()
        }
}


