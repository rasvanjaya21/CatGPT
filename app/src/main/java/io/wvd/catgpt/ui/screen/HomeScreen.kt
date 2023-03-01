package io.wvd.catgpt.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.wvd.catgpt.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = hiltViewModel()
){

    val isRefreshing by mainViewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { mainViewModel.refresh() })

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.primary,
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .pullRefresh(pullRefreshState)
            ) {
                LazyColumn(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                    item {
                        HomeContent()
                    }
                }
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    contentColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        },
    )

}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Hello Meow",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}