package ua.dev.webnauts.cleanproject.screen.base_creen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.dev.webnauts.cleanproject.R

@Preview
@Composable
fun FinishScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {

            Text(
                text = "Добро пожаловать в эксклюзивное сообщество, где ваши финансовые мечты станут реальностью! Вы лучше чем 769 пользователей приложения!",
                color = Color.White,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 19.sp,
                    lineHeight = 23.sp,
                    fontStyle = FontStyle.Normal
                )
            )


            Column(
                modifier = Modifier
                    .border(2.dp, color = Color.White, shape = RoundedCornerShape(15.dp)),
                verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = "№",
                        color = Color.White.copy(.49f),
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 12.sp,
                            lineHeight = 23.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )

                    Text(
                        text = "Пользователь",
                        modifier = Modifier.weight(1f),
                        color = Color.White.copy(.49f),
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 12.sp,
                            lineHeight = 23.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )

                    Text(
                        text = "Балы",
                        color = Color.White.copy(.49f),
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 12.sp,
                            lineHeight = 23.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )
                }

                Rows(1, "Вы", 100)
                Rows(2, "Юрий", 75)
                Rows(3, "Олег", 75)
                Rows(4, "Максим", 60)
                Rows(5, "Никита", 45)
                Rows(6, "Сергей", 30)
            }


            Text(
                text = "В течение дня наш менеджер свяжется с вами, чтобы подробно объяснить, как вы можете забрать свои акции!",
                color = Color.White.copy(alpha = .62f),
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 17.sp,
                    fontStyle = FontStyle.Normal
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.box),
                    contentDescription = "box",
                    modifier = Modifier.size(70.dp)
                )

                Text(
                    text = "Забрать подарок вы можете нажав просто на нопку",
                    modifier = Modifier.weight(.8f),
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 17.sp,
                        fontStyle = FontStyle.Normal
                    )
                )

            }


            Button(
                modifier = Modifier.fillMaxWidth(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF250AB4),
                    disabledContainerColor = Color(0xFF250AB4)
                ),
                onClick = {}) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    androidx.compose.material3.Text(
                        text = "Скачать", style = TextStyle(
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp,
                            lineHeight = 19.sp,
                            fontStyle = FontStyle.Normal
                        )
                    )
                    Icon(
                        modifier = Modifier.size(26.dp),
                        painter = painterResource(id = R.drawable.pdf_file),
                        contentDescription = "pdf file", tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 40.dp))

        }
    }
}


@Composable
fun Rows(number: Int, userName: String, balls: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = "$number",
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 17.sp,
                fontStyle = FontStyle.Normal
            )
        )

        Text(
            text = "$userName",
            modifier = Modifier.weight(1f),
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 17.sp,
                fontStyle = FontStyle.Normal
            )
        )

        Text(
            text = "$balls",
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 17.sp,
                fontStyle = FontStyle.Normal
            )
        )
    }
}