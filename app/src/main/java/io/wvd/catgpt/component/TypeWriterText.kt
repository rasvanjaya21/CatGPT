package io.wvd.catgpt.component

import android.util.Log
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlin.streams.toList

@Composable
fun TypewriterText() {

//    Text(
//        text = textToDisplay,
//        color = MaterialTheme.colorScheme.primary,
//    )
}

fun String.splitToCodePoints(): List<String> {
    return codePoints()
        .toList()
        .map {
            String(Character.toChars(it))
        }
}