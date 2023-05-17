package com.blake.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blake.data.dto.RouteDto
import com.blake.presentation.viewmodel.RoutesViewModel

@ExperimentalMaterial3Api
@Composable
fun RouteScreen(vm: RoutesViewModel = hiltViewModel()) {
    val route = vm.route.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row { Text("Route") } }
            )
        },
        content = { padding ->
            if (vm.errorMessage.isEmpty()) {
                Box(modifier = Modifier.padding(padding)) {
                    RouteItem(route.value)
                }
            } else {
                Text(vm.errorMessage)
            }
        }
    )
}

@Composable
private fun RouteItem(route: RouteDto) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Type : ${route.type}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Name : ${route.name}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}