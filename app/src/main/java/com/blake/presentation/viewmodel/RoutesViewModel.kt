package com.blake.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blake.data.dto.RouteDto
import com.blake.data.storage.DriverDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val driverDataRepository: DriverDataRepository
) : ViewModel() {

    private val driverId: String = checkNotNull(savedStateHandle["driverId"])

    private val _route = MutableStateFlow(RouteDto())
    var errorMessage: String by mutableStateOf("")

    val route: StateFlow<RouteDto>
        get() = _route.asStateFlow()

    init {
        viewModelScope.launch {
            val routes = driverDataRepository.getRoutes()
            //a.
            var route: RouteDto? = routes.find { it.id == driverId }
            //b.
            if (route == null && driverId.toInt() % 2 == 0) {
                route = routes.first { it.isRType }
            }
            //c.
            if (route == null && driverId.toInt() % 5 == 0) {
                val cTypeRoutes = routes.filter { it.isCType }
                if (cTypeRoutes.size >= 2) {
                    route = cTypeRoutes[1]
                }
            }
            //d.
            if (route == null) {
                route = routes.last() { it.isIType }
            }
            _route.value = route
        }
    }
}