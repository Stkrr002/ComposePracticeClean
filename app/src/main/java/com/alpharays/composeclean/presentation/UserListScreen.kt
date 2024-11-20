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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alpharays.composeclean.utils.APIResponse

@Composable
fun UserListScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    // State to hold left and right lists
    var leftList by remember { mutableStateOf<List<String>>(emptyList()) }
    var rightList by remember { mutableStateOf<List<String>>(emptyList()) }

    // Collect the API response
    val userListState = userViewModel.userListFlow.collectAsState()

    // Effect to handle the API response and distribute items between lists
    LaunchedEffect(userListState.value) {
        when (val response = userListState.value) {
            is APIResponse.Success -> {
                response.data?.let { fullList ->
                    if (fullList.size <= 20) {
                        leftList = fullList
                        rightList = emptyList()
                    } else {
                        leftList = fullList.take(20)
                        rightList = fullList.drop(20)
                    }
                }
            }

            is APIResponse.Loading -> {
                // Handle loading state
            }

            is APIResponse.Error -> {
                // Handle error state
            }

            is APIResponse.Empty -> {
                leftList = emptyList()
                rightList = emptyList()
            }
        }
    }

    // Effect to fetch user list when screen is launched
    LaunchedEffect(Unit) {
        userViewModel.getUserList()
    }

    Scaffold { paddingValues ->
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
                        text = "Left List (Max 20 items)",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    LazyColumn {
                        items(leftList) { item ->
                            ListItem(item)
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
                    LazyColumn {
                        items(rightList) { item ->
                            ListItem(item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItem(item: String) {
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