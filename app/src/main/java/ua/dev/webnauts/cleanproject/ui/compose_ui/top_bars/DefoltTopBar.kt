package ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.ui.theme.Typography

@Composable
fun DefaultTopBar(title : String, clickable: Boolean = true, onClick: () -> Unit = {}) {
    Row(modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier
            .width(20.dp))

        Text(text = title, style = Typography.bodyLarge)

        Spacer(modifier = Modifier
            .weight(1f))
    }
}