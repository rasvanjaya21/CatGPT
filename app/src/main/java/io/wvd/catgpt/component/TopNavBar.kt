package io.wvd.catgpt.component

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.wvd.catgpt.util.TopNavBarItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar() {

    val items = listOf(TopNavBarItem.Default)
    val openInfoApp = remember { mutableStateOf(false) }

    items.forEach { item ->
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.primary).padding(bottom = 10.dp),
            title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        modifier = Modifier,
                        text = item.title,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        modifier = Modifier,
                        text = item.subTitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontStyle = FontStyle.Italic
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    openInfoApp.value = true
                }) {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = "Info App"
                    )
                }
            }
        )
    }

    if (openInfoApp.value) {
        DialogInfo(openInfoApp = openInfoApp)
    }

}