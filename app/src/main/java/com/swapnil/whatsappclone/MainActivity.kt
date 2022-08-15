package com.swapnil.whatsappclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swapnil.whatsappclone.ui.Utils
import com.swapnil.whatsappclone.ui.theme.Green500
import com.swapnil.whatsappclone.ui.theme.WhatsAppCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppCloneTheme {
                WhatsAppScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WhatsAppCloneTheme {
        Greeting("Android")
    }
}

@Composable
fun WhatsAppScreen() {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Green500) { ToolBar() }
    }) {
        ContentScreen()
    }
}

@Composable
fun ToolBar() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h6,
                text = "WhatsApp",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
            TopAppBarIcons()
        }
    }
}

@Composable
fun TopAppBarIcons() {
    IconButton(onClick = { }) {
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = null, tint = Color.White
        )
    }
    IconButton(onClick = { }) {
        Icon(
            imageVector = Icons.Rounded.Message,
            contentDescription = null, tint = Color.White
        )
    }
    IconButton(onClick = { }) {
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null, tint = Color.White
        )
    }
}

@Composable
fun ContentScreen() {
    var selectedIndex by remember {
        mutableStateOf(Screens.CHATS.ordinal)
    }
    Column() {
        TabRow(modifier = Modifier.height(56.dp), selectedTabIndex = selectedIndex) {
            Screens.values().forEach { tab ->
                Tab(
                    selected = selectedIndex == tab.ordinal,
                    onClick = { selectedIndex = tab.ordinal }) {
                    Text(text = tab.name)
                }
            }
        }
        val lastIndex = Utils.Messages.messageList.size - 1
        when (selectedIndex) {
            0 -> {
                LazyColumn() {
                    itemsIndexed(Utils.Messages.messageList) { currIndex, message ->
                        MessageCard(
                            message = message,
                            currIndex == lastIndex
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ToolbarPrev() {
    ToolBar()
}