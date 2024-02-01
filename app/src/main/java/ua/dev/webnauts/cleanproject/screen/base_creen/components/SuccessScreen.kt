package ua.dev.webnauts.cleanproject.screen.base_creen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.ui.compose_components.TextField.TextFieldWithTitle

@Preview
@Composable
fun SuccessScreen() {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(15.dp))
            .padding(vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "Поздравляем".uppercase(),
                color = Color.Black,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.W700,
                    fontSize = 19.sp,
                    lineHeight = 23.sp,
                    fontStyle = FontStyle.Normal
                )
            )
            Text(
                text = "У вас прекрасное чувство рынка.\nЧтобы получить 10 подарочных акций завершите регистрацию",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    fontStyle = FontStyle.Normal
                )
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextFieldWithTitle(
                placeholder = {
                    Text(text = "Ваше имя",
                        color = Color.Black.copy(alpha = .3f),
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            lineHeight = 19.sp,
                            fontStyle = FontStyle.Italic
                        ))
                },
                value = name,
                onValueChange = {
                    name = it
                }
            )
            TextFieldWithTitle(
                placeholder = {
                    Text(text = "Ваша фамилия",
                        color = Color.Black.copy(alpha = .3f),
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            lineHeight = 19.sp,
                            fontStyle = FontStyle.Italic
                        ))
                },
                value = lastName,
                onValueChange = {
                    lastName = it
                }
            )
            TextFieldWithTitle(
                placeholder = {
                    Text(text = "Ваш номер телефона",
                        color = Color.Black.copy(alpha = .3f),
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            lineHeight = 19.sp,
                            fontStyle = FontStyle.Italic
                        ))
                },
                value = phone,
                onValueChange = {
                    phone = it
                }
            )
        }


        Button(
            modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 30.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF250AB4),
                disabledContainerColor = Color(0xFF250AB4).copy(alpha = .5f)
            ),
            onClick = { }) {

            androidx.compose.material3.Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = "Зарегистрироваться", style = TextStyle(
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    fontStyle = FontStyle.Normal
                )
            )
        }
    }
}
