package io.wvd.catgpt.ui.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import io.wvd.catgpt.component.BottomBar
import io.wvd.catgpt.component.CatList
import io.wvd.catgpt.component.TopBar
import io.wvd.catgpt.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LayoutScreen(
    mainViewModel: MainViewModel = hiltViewModel()
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { TopBar(keyboardController = keyboardController, focusManager = focusManager) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        })
                    }
            ) {
                HomeScreen(
                    mainViewModel = mainViewModel,
                    items = mainViewModel.cats,
                    keyboardController = keyboardController,
                    focusManager = focusManager
                )
//                CatList(
//                    mainViewModel = mainViewModel,
//                    cats = mainViewModel.cats,
//                    onAddUser = { mainViewModel.addUser(it) },
//                    onRemove = { mainViewModel.removeCat(it) }
//                )
            }
        },
        bottomBar = {
            BottomBar(
                mainViewModel = mainViewModel,
                keyboardController = keyboardController,
                focusManager = focusManager,
                onAddUser = { mainViewModel.addUser(it) },
            )},
    )
}