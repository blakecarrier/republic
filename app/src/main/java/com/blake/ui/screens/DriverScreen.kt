package com.blake.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blake.data.dto.DriverDto
import com.blake.presentation.viewmodel.DriversViewModel

@ExperimentalMaterial3Api
@Composable
fun DriverScreen(
    vm: DriversViewModel = hiltViewModel(),
    onNavigateToRoute: (String) -> Unit
) {
    Scaffold(
        topBar = { AppBar(vm) },
        content = { padding ->
            if (vm.errorMessage.isEmpty()) {
                Column(modifier = Modifier.padding(padding)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(vm.driverList.size) { position ->
                            DriverItem(vm.driverList[position], onNavigateToRoute)
                        }
                    }
                }
            } else {
                Text(vm.errorMessage)
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
private fun AppBar(vm: DriversViewModel) {
    TopAppBar(
        title = { Row { Text("Drivers") } },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = {
                vm.sortData()
            }) {
                Icon(Icons.Filled.Sort, contentDescription = null)
            }
        }
    )
}

@Composable
private fun DriverItem(
    driverDto: DriverDto,
    onNavigateToRoute: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToRoute.invoke(driverDto.id) }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${driverDto.name} ( ${driverDto.id} )",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Divider()
    }
}