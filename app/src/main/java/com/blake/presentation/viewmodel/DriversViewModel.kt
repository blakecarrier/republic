package com.blake.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blake.data.dto.DriverDto
import com.blake.data.storage.DriverDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriversViewModel @Inject constructor(
    private val driverDataRepository: DriverDataRepository
) : ViewModel() {

    private val _driverList = mutableStateListOf<DriverDto>()

    var errorMessage: String by mutableStateOf("")
    val driverList: List<DriverDto>
        get() = _driverList

    init {
        getDrivers()
    }

    private fun getDrivers() {
        viewModelScope.launch {
            try {
                _driverList.addAll(driverDataRepository.getDrivers())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun sortData() {
        viewModelScope.launch {
            _driverList.sortBy { it.lastName }
        }
    }
}