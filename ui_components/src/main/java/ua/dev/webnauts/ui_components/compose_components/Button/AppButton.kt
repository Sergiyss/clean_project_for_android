package ua.dev.webnauts.cleanproject.ui.compose_components.Button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ua.dev.webnauts.ui_components.ui.theme.customShapes
import ua.dev.webnauts.ui_components.ui.theme.spacing


/**
 * Button
 * @param onClick действие
 * @param modifier Modifier
 * @param enabled true or false
 * @param text текст внутри кнопки
 */
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            disabledBackgroundColor = MaterialTheme.colors.primary.copy(.5f),
            disabledContentColor = Color.White
        ),
        shape = MaterialTheme.customShapes.small
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                color = Color.White
            )
        } else {
            Text(modifier = Modifier.padding(MaterialTheme.spacing.small),
                text = text.uppercase(),
                style = MaterialTheme.typography.button
            )
        }
    }
}