package com.swapnil.whatsappclone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.swapnil.whatsappclone.ui.Utils
import com.swapnil.whatsappclone.ui.model.Message

@Composable
fun MessageCard(message: Message,showDivider:Boolean) {
    Row() {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(message.profileUrl)
                .transformations(CircleCropTransformation()).build(),
            contentDescription = "testing",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .clip(
                    CircleShape
                )
                .size(72.dp)
        , loading = { CircularProgressIndicator()}
        )
        Column(
            Modifier
                .weight(1f)

        ) {
            Row() {
                Text(
                    message.userName,
                    Modifier.padding(start = 16.dp, top = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = message.time,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = message.lastMessage,
                Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
    if (!showDivider) {
        Divider(modifier = Modifier.padding(top = 16.dp), color = Color.Black)
    }
}

@Preview
@Composable
fun MessageCardPrev() {
    MessageCard(message = Utils.Messages.messageList[0],false)
}