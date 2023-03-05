package io.wvd.catgpt.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.wvd.catgpt.R
import io.wvd.catgpt.data.Cat
import io.wvd.catgpt.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun BottomBar(
    mainViewModel: MainViewModel,
    onAddUser: (Cat) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
){

    val inputText by mainViewModel.inputText.collectAsState()
    val isRefreshing by mainViewModel.isRefreshing.collectAsState()

    Box(modifier = Modifier.padding(20.dp)) {
        TextField(
            value = inputText,
            enabled = !isRefreshing,
            onValueChange = { mainViewModel.setInputText(it) },
            maxLines = 1,
            placeholder = { Text(text = "Type your message here") },
            shape = MaterialTheme.shapes.large,
            trailingIcon = {
                IconButton(
                    onClick = {
                        if(inputText !== "") {
                            keyboardController?.hide()
                            mainViewModel.addInputData(inputText)
                            mainViewModel.resetInputText()
                            focusManager.clearFocus()
                            onAddUser(mainViewModel.showUserInput(inputText))
//                            if(inputText == "hi") {
//                                mainViewModel.addCat(Cat(name = "hello", catImage = R.drawable.avatar_meow))
//                            }
                        }
                    },
                    enabled = !isRefreshing,
                ) {
                    Icon(
                        painterResource(id = R.drawable.filled_send_icon),
                        contentDescription = "Send Text"
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = MaterialTheme.colorScheme.surface,
                placeholderColor = Color(0xFFCAC4D0),
                unfocusedTrailingIconColor = Color(0xFFCAC4D0),
                focusedTrailingIconColor = Color.White,
                disabledPlaceholderColor = Color(0xFF7F7E88),
                disabledTrailingIconColor = Color(0xFF7F7E88),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = {
                    if(inputText !== "") {
                        keyboardController?.hide()
                        mainViewModel.resetInputText()
                        focusManager.clearFocus()
                    }
                }
            )
//            keyboardActions = i
        )
    }
//    Text("The text field has this text: $inputText")
}