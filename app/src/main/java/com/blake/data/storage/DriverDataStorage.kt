package com.blake.data.storage

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import androidx.core.content.edit
import com.blake.data.dto.DriverDto
import com.blake.data.dto.RouteDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

interface DriverDataStorage {
    suspend fun getDrivers(): List<DriverDto>
    suspend fun getRoutes(): List<RouteDto>
    suspend fun saveDrivers(drivers: List<DriverDto>)
    suspend fun saveRoutes(routes: List<RouteDto>)
}

class DriverDataStorageImpl @Inject constructor(
    private val gson: Gson,
    private val preferences: SharedPreferences,
) : DriverDataStorage {

    override suspend fun getDrivers(): List<DriverDto> {
        val serializedData = preferences.getString(KEY_DRIVERS, "")
        return try {
            gson.fromJson(serializedData, object : TypeToken<List<DriverDto>>() {}.type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getRoutes(): List<RouteDto> {
        val serializedData = preferences.getString(KEY_ROUTES, "")
        return try {
            gson.fromJson(serializedData, object : TypeToken<List<RouteDto>>() {}.type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun saveDrivers(drivers: List<DriverDto>) {
        preferences.edit {
            putString(KEY_DRIVERS, gson.toJson(drivers))
        }
    }

    override suspend fun saveRoutes(routes: List<RouteDto>) {
        preferences.edit {
            putString(KEY_ROUTES, gson.toJson(routes))
        }
    }

    companion object {
        @VisibleForTesting
        const val KEY_DRIVERS = "key_drivers"

        @VisibleForTesting
        const val KEY_ROUTES = "key_routes"
    }
}
