package io.wvd.catgpt.component

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.wvd.catgpt.util.TopBarItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TopBar(focusManager: FocusManager, keyboardController: SoftwareKeyboardController?) {

    val items = listOf(TopBarItem.Default)
    val openInfoApp = remember { mutableStateOf(false) }

    items.forEach { item ->
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = 10.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    })
            },
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
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = FontStyle.Italic
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    openInfoApp.value = true
                    keyboardController?.hide()
                    focusManager.clearFocus()
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