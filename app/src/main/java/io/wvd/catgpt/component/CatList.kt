package io.wvd.catgpt.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import io.wvd.catgpt.R
import androidx.compose.ui.unit.dp
import io.wvd.catgpt.data.Cat
import io.wvd.catgpt.viewmodel.MainViewModel

@Composable
fun CatList(
    cats: List<Cat>,
    onRemove: (Cat) -> Unit,
    onAddUser: (Cat) -> Unit,
    mainViewModel: MainViewModel,
) {

    val inputText by mainViewModel.inputText.collectAsState()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            onAddUser(mainViewModel.showUserInput(inputText))
            if(inputText == "hi") {
                mainViewModel.addCat(Cat(name = "hello", catImage = R.drawable.avatar_meow))
            }
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(cats) { cat ->
                CatListItem(cat = cat, onRemove = onRemove)
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatListItem(cat: Cat, onRemove: (Cat) -> Unit) {
    ListItem(
        modifier = Modifier
            .clickable { onRemove(cat) }
            .padding(8.dp),
        icon = {
            Image(
                painter = painterResource(id = cat.catImage),
                contentDescription = null,
                modifier = Modifier.clip(MaterialTheme.shapes.small)
            )
        },
        text = { Text(text = cat.name, style = MaterialTheme.typography.h6) },
    )
}








