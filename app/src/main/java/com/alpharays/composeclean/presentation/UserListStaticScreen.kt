package com.alpharays.composeclean.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun UserListStaticScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    // State to hold the list items
    val items = userViewModel.userListFlow.collectAsState()

    Scaffold(
        bottomBar = {
            Button(
                onClick = { userViewModel.addItem() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Add Item")
            }
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Left List
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Left List (First 20 items)",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    LazyColumn {
                        items(items.value.data?.take(20) ?: emptyList()) { item ->
                            ListItemStatic(item)
                        }
                    }
                }
            }

            // Right List
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Right List (Overflow items)",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                LazyColumn {
                    val overflowItems = items.value.data?.drop(20) ?: emptyList()
                    items(overflowItems) { item ->
                        ListItemStatic(item)
                    }
                }
            }
        }
    }
}


@Composable
private fun ListItemStatic(item: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = item,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}