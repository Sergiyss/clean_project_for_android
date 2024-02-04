package ua.dev.webnauts.cleanproject.ui.compose_components.photo_text_field

import android.content.Context
import android.telephony.TelephonyManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import kotlinx.serialization.SerialName
import ua.dev.webnauts.cleanproject.ui.compose_components.TextField.AppTextField
import ua.dev.webnauts.cleanproject.ui.compose_components.text_field.AppTextFieldTextFieldValue
import ua.dev.webnauts.cleanproject.ui.theme.customShapes
import ua.dev.webnauts.cleanproject.ui.theme.spacing


data class Country(
    @SerialName("id")
    val id: Int? = null,
    val code: String? = null,
    val dialCode: String? = null,
)


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    context: Context,
    value: TextFieldValue,
    title: String? = null,
    onCountryChange: (phone: TextFieldValue, country: Country) -> Unit,
    onValueChange: (phone: TextFieldValue, country: Country) -> Unit,
    changeable: Boolean = true,
    defaultCountry: Country? = null,
    countries: List<Country>,
    error: Boolean = false,
    errorText: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var defaultLang by remember(defaultCountry) {
        mutableStateOf(
            defaultCountry?.code
                ?: getDefaultLangCode(context)
        )
    }
    var selectedCountry by remember(defaultCountry, countries.size) {
        mutableStateOf(
            if (countries.isEmpty()) defaultCountry
            else countries.getCountry(defaultCountry?.code ?: defaultLang)
        )
    }

    LaunchedEffect(defaultCountry, defaultLang) {
        selectedCountry = countries.getCountry(defaultLang) ?: defaultCountry
    }

    LaunchedEffect(selectedCountry) {
        selectedCountry?.let {
            onCountryChange(
                TextFieldValue(
                    it.dialCode.toString(),
                    selection = TextRange(it.dialCode?.length ?: 0)
                ),
                it
            )
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    selectedCountry?.let { country ->
        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
            title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1
                )
            }
            AppTextFieldTextFieldValue(
                value = toPhone(value),
                onValueChange = {
                        if(it.text.length <= 13) {
                            onValueChange(toPhone(it), country)
                        }
                    },
                modifier = modifier,
                shape = MaterialTheme.customShapes.small,
                textStyle = MaterialTheme.typography.subtitle1,
                singleLine = true,
                placeholder = {
                    Text(text = country.dialCode.toString())
                },
                visualTransformation = PhoneNumberVisualTransformation(
                    country.code?.uppercase().toString()
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    autoCorrect = true,
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                leadingIcon = {

                },
                trailingIcon = trailingIcon,
                error = error,
                errorText = errorText
            )
        }
    }
}

fun Collection<Country>.getCountry(countryCode: String): Country? {
    if (this.isEmpty()) return null
    return try {
        this.single { it.code == countryCode }
    } catch (_: Exception) {
        this.first()
    }
}

fun getDefaultLangCode(context: Context): String {
    val localeCode: TelephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val countryCode = localeCode.networkCountryIso
    val defaultLocale = Locale.current.language
    val lang = countryCode.ifBlank { defaultLocale }
    return lang.ukToUaOrDefault()
}

fun String.ukToUaOrDefault(): String {
    return if (this == "uk") "ua" else this
}

fun toPhone(textFieldValue: TextFieldValue): TextFieldValue {
    if (textFieldValue.text.isEmpty()) return TextFieldValue(
        "",
        selection = TextRange.Zero
    )
    return when (textFieldValue.text.last().toString().toIntOrNull()) {
        null -> {
            TextFieldValue(
                textFieldValue.text.dropLast(1),
                selection = TextRange(textFieldValue.text.dropLast(1).length)
            )
        }
        else -> {
            if (textFieldValue.text.isNotEmpty() && textFieldValue.text[0] != '+') {
                val replacedNumber = textFieldValue.text.replaceRange(0, 0, "+")
                TextFieldValue(
                    replacedNumber,
                    selection = TextRange(replacedNumber.length)
                )
            } else if (textFieldValue.text.length == 1 && textFieldValue.text[0] == '+')
                TextFieldValue(
                    "",
                    selection = TextRange("".length)
                )
            else textFieldValue
        }
    }
}