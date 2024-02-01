package ua.dev.webnauts.cleanproject.ui.compose_components.TextField

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import ua.dev.webnauts.cleanproject.ui.theme.spacing

@Composable
fun TextFieldWithTitle(
    title: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    error: Boolean = false,
    errorText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        title?.let{t->
            Text(
                text = t,
                style = MaterialTheme.typography.body1
            )
        }
        AppTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier,
            keyboardOptions = keyboardOptions,
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                letterSpacing = 0.15.sp,
                color = Color.Black
            ),
            visualTransformation = visualTransformation,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = true,
            error = error,
            errorText = errorText
        )
    }
}