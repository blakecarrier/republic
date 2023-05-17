package com.blake.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.blake.MainCoroutineRule
import com.blake.data.dto.RouteDto
import com.blake.data.storage.DriverDataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class RoutesViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private val repository: DriverDataRepository = mock(DriverDataRepository::class.java)
    private lateinit var vm: RoutesViewModel

    @Test
    fun `Driver id same as route id`() = runTest {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["driverId"] = "9"
        whenever(repository.getRoutes()).thenReturn(testRoutes())
        vm = RoutesViewModel(savedStateHandle, repository)

        Assert.assertEquals("9", vm.route.value.id)
    }

    @Test
    fun `Driver divisible by 2 first r type`() = runTest {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["driverId"] = "24"
        whenever(repository.getRoutes()).thenReturn(testRoutes())
        vm = RoutesViewModel(savedStateHandle, repository)

        Assert.assertEquals("1", vm.route.value.id)
    }

    @Test
    fun `Driver divisible by 5 second c type`() = runTest {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["driverId"] = "35"
        whenever(repository.getRoutes()).thenReturn(testRoutes())
        vm = RoutesViewModel(savedStateHandle, repository)

        Assert.assertEquals("5", vm.route.value.id)
    }

    @Test
    fun `Driver meets no conditions`() = runTest {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["driverId"] = "37"
        whenever(repository.getRoutes()).thenReturn(testRoutes())
        vm = RoutesViewModel(savedStateHandle, repository)

        Assert.assertEquals("12", vm.route.value.id)
    }


    private fun testRoutes(): List<RouteDto> {
        return listOf(
            RouteDto("1", "R", "West Side Residential Route"),
            RouteDto("2", "C", "West Side Commercial Route"),
            RouteDto("3", "I", "West Side Industrial Route"),
            RouteDto("4", "R", "East Side Residential Route"),
            RouteDto("5", "C", "East Side Commercial Route"),
            RouteDto("6", "I", "East Side Industrial Route"),
            RouteDto("7", "R", "North Side Residential Route"),
            RouteDto("8", "C", "North Side Commercial Route"),
            RouteDto("9", "I", "North Side Industrial Route"),
            RouteDto("10", "R", "South Side Residential Route"),
            RouteDto("11", "C", "South Side Commercial Route"),
            RouteDto("12", "I", "South Side Industrial Route"),
        )
    }
}