package ua.dev.webnauts.ui_components.compose_components.TextField

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ua.dev.webnauts.ui_components.ui.theme.customShapes
import ua.dev.webnauts.ui_components.ui.theme.spacing

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    errorText: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.subtitle1,
    textPaddingValues: PaddingValues = PaddingValues(MaterialTheme.spacing.medium, 0.dp),
    alignmentVertical: Alignment.Vertical = Alignment.CenterVertically,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Color.Black),
    shape: Shape = MaterialTheme.customShapes.small,
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    background: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.onBackground,
    elevation: Dp = 6.dp,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            cursorBrush = cursorBrush
        ) { innerTextField ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = shape,
                backgroundColor = if (enabled) background else MaterialTheme.colors.onBackground.copy(
                    .05f
                ).compositeOver(
                    Color.White
                ),
                contentColor = if (enabled) contentColor else MaterialTheme.colors.onBackground.copy(
                    .2f
                ).compositeOver(
                    Color.White
                ),
                border = if (!error) borderStroke else BorderStroke(
                    1.dp,
                    MaterialTheme.colors.error
                ),
                elevation = elevation
            ) {
                Row(
                    modifier = Modifier
                        .padding(textPaddingValues)
                        .fillMaxWidth()
                        .width(IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = alignmentVertical
                ) {
                    leadingIcon?.invoke()
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = MaterialTheme.spacing.medium),
                        propagateMinConstraints = true
                    ) {
                        if (value == "" && value.isEmpty()) placeholder?.invoke()
                        innerTextField()
                    }
                    trailingIcon?.invoke()
                }
            }
        }
        errorText?.let {
            ErrorText(text = it)
        }
    }
}


@Composable
fun ErrorText(
    text: String,
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.error)
    )
}