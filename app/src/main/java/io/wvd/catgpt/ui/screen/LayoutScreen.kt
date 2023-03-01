package io.wvd.catgpt.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import io.wvd.catgpt.component.TopNavBar
import io.wvd.catgpt.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutScreen(){
    Scaffold(
        topBar = { TopNavBar() },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
            ) {
                HomeScreen()
            }
        },
        bottomBar = {
            Text("Hello World")
        },
    )
}