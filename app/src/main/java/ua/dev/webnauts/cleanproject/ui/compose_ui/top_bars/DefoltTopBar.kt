package ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultTopBar(title : String, clickable: Boolean = true, onClick: () -> Unit = {}) {
    Row {
        Button(onClick = {
            if(clickable){ onClick() }
        }) {
            Text(text = "Back")
        }

        Spacer(modifier = Modifier.weight(1f).clickable {  })

        Text(text = title)
    }
}