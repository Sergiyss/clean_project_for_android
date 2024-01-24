package ua.dev.webnauts.cleanproject.test_project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ua.dev.webnauts.cleanproject.repository.Download


import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.items

import androidx.compose.material.*

import androidx.compose.runtime.*
import ua.dev.webnauts.cleanproject.workers.RESULT_WORK_KEY

import java.util.*
import kotlin.random.Random


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TestWorker(
    onDownloadUrl: (downloadUrl: String) -> Unit,
    onClear: () -> Unit,
    downloads: State<List<Download>>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var url by rememberSaveable { mutableStateOf("") }
    Column(
    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
    horizontalAlignment = Alignment.Start,
    ) {


        OutlinedTextField(
            label = { Text("Download url") },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            value = url,
            onValueChange = { value -> url = value },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDownloadUrl(url)
                    keyboardController?.hide()
                })
        )

        OutlinedButton(
            onClick = {
                onDownloadUrl(url)
                keyboardController?.hide()
            },
            content = { Text(text = "Download") })



        LazyColumn {
            items(
                items = downloads.value,
                key = Download::id,
                itemContent = { download ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        elevation = 4.dp
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            content = {
                                Column(
                                    modifier = Modifier
                                        .weight(weight = 1.0F)
                                        .padding(all = 8.dp),
                                    content = {
                                        SpannableText(id = "Id : ", content = download.id)
                                        SpannableText(
                                            id = "Attempt : ",
                                            content = download.downloadAttempt.toString()
                                        )
                                        SpannableText(id = "State : ", content = download.state)
                                        SpannableText(id = "Url : ", content = download.urlString)
                                        SpannableText(
                                            id = "Filename : ",
                                            content = download.outputFilename
                                        )
                                        SpannableText(
                                            id = "RESULT : ", content = download.result
                                        )
                                    })

                            })
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                })
        }
    }

}


@Composable
private fun SpannableText(id: String, content: String) {
    Text(
        modifier = Modifier.padding(all = 4.dp),
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(Color.Blue)) {
                append(id)
            }
            append(content)
        })
}
