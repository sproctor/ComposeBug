package com.seanproctor.composebug

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import composebug.composeapp.generated.resources.Res
import composebug.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var expandHeader by remember { mutableStateOf(false) }
        Row {
            WideNavigationRail(
                header = {
                    if (showContent) {
                        Box(modifier = Modifier.height(if (!expandHeader) 100.dp else 200.dp).background(Color.Red)) {
                            Text("Header")
                        }
                    }
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    for (i in 1..5) {
                        NavigationRailItem(
                            selected = false,
                            onClick = {},
                            icon = {},
                            label = {
                                Text("Item $i")
                            }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me to add header")
                }
                Button(onClick = { expandHeader = !expandHeader }) {
                    Text("Click me to expand/collapse header")
                }
            }
        }
    }
}