package io.wvd.catgpt.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.wvd.catgpt.R

@Composable
fun DialogInfo(
    openInfoApp: MutableState<Boolean>,
) {

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.secondary,
        onDismissRequest = {
            openInfoApp.value = false
        },
        title = {
            Image(painter = painterResource(id = R.drawable.about_app) , contentDescription = "About App", modifier = Modifier.fillMaxWidth().clip(MaterialTheme.shapes.large))
        },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState()).fillMaxWidth()) {
                Text(
                    text = "Application Version",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "0.0.2",
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Release Status",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Beta Stable",
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Codename",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Meow",
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Privacy Policy",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "CatGPT Website",
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Contributors",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "@rasvanjaya21",
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Social Media",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Github",
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openInfoApp.value = false
                }
            ) {
                Text("Close", color = MaterialTheme.colorScheme.primary)
            }
        }
    )
}