package com.seanproctor.composebug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seanproctor.composebug.ui.theme.AppTheme
import composebug.composeapp.generated.resources.Res
import composebug.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        var showContent by remember { mutableStateOf(false) }
        var expandHeader by remember { mutableStateOf(false) }
        val railState = rememberWideNavigationRailState(WideNavigationRailValue.Expanded)
        val coroutineScope = rememberCoroutineScope()
        var selectedItem by remember { mutableStateOf(0) }
        Row {
            WideNavigationRail(
                state = railState,
                header = {
                    if (showContent) {
                        Box(
                            modifier = Modifier.height(if (!expandHeader) 100.dp else 200.dp)
                                .background(Color.Red)
                        ) {
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
                        WideNavigationRailItem(
                            selected = selectedItem == i,
                            onClick = {
                                selectedItem = i
                            },
                            icon = {
                                Icon(
                                    painterResource(Res.drawable.compose_multiplatform),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text("Item $i")
                            },
                            railExpanded = railState.currentValue == WideNavigationRailValue.Expanded
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
                Button(
                    onClick = {
                        coroutineScope.launch {
                            railState.toggle()
                        }
                    }
                ) {
                    Text("Click me to toggle rail")
                }
            }
        }
    }
}