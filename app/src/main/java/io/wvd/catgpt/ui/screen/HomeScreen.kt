package io.wvd.catgpt.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.wvd.catgpt.R
import io.wvd.catgpt.component.TypewriterText
import io.wvd.catgpt.component.splitToCodePoints
import io.wvd.catgpt.data.Cat
import io.wvd.catgpt.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    items: List<Cat>,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
){

    val isRefreshing by mainViewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { mainViewModel.refresh() })
    val inputData by mainViewModel.inputData.collectAsState()

    val listState = rememberLazyListState()
    val itemHeight = with(LocalDensity.current) { 80.dp.toPx() } // Your item height
    val scrollPos = listState.firstVisibleItemIndex * itemHeight + listState.firstVisibleItemScrollOffset

    var textIndex by remember { mutableStateOf(0) }
    var textToDisplay by remember { mutableStateOf("") }
    val texts = listOf("Meow, meow meow meow, meow meow?")
    val textCharsList: List<List<String>> = remember {
        texts.map {
            it.splitToCodePoints()
        }
    }

    LaunchedEffect(Unit) {
        textCharsList[textIndex].forEachIndexed { charIndex, _ ->
            textToDisplay = textCharsList[textIndex]
                .take(
                    n = charIndex + 1,
                ).joinToString(
                    separator = "",
                )
            delay(100)
        }
        textIndex = (textIndex + 1) % texts.size
    }

    if(isRefreshing){
        keyboardController?.hide()
        focusManager.clearFocus()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .pullRefresh(pullRefreshState)
            ) {
                LazyColumn(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize(), state = listState) {
                    item {
                        MeowChat(textToDisplay)
                    }
                    items(items) { cat ->
                        UserChat(cat = cat)
                        MeowChat(textToDisplay)
                    }
                }
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    contentColor = MaterialTheme.colorScheme.background,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        },
    )

}

@Composable
fun MeowChat(
    textToDisplay: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.avatar_meow), contentDescription = "Avatar Meow", modifier = Modifier
                .size(35.dp)
                .clip(MaterialTheme.shapes.extraSmall))
//            TypewriterText()
            Text(
                text = textToDisplay,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }

}

@Composable
fun UserChat(
    cat: Cat
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = cat.catImage), contentDescription = "Avatar User", modifier = Modifier
                .size(35.dp)
                .clip(MaterialTheme.shapes.extraSmall))
            Text(
                text = cat.name,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

}

//@Composable
//fun HomeContent(
//    mainViewModel: MainViewModel
//) {
//    val inputText by mainViewModel.inputText.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//    ) {
//        Text(
//            text = "Hello $inputText",
//            fontWeight = FontWeight.Bold,
//            color = MaterialTheme.colorScheme.primary,
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            fontSize = 20.sp
//        )
//    }
//}
