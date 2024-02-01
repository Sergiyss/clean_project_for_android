package ua.dev.webnauts.cleanproject.screen.base_creen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


@Preview
@Composable
fun TitleQues(title: String = "Сбербанк объявил о раздаче рекордных дивидендов за этот квартал."){
    Column(
        modifier = Modifier
            .border(2.dp, color = Color.White, shape = RoundedCornerShape(15.dp))
            .background(color = Color(0xFF156770), shape = RoundedCornerShape(15.dp))
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 19.sp,
                lineHeight = 23.sp,
                fontStyle = FontStyle.Normal
            )
        )
    }
}


@Preview
@Composable
fun Questionnaire(
    title: String = "Вопрос 1/4",
    subTitle: String = "Как себя поведут акции SBER (Сбербанк) после данной новости?",
    up:()-> Unit = {}, down:()-> Unit = {}
) {
    Column(
        modifier = Modifier
            .border(2.dp, color = Color.White, shape = RoundedCornerShape(15.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = title,
                color = Color.White,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    fontStyle = FontStyle.Normal
                )
            )
            Text(
                text = subTitle,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    fontStyle = FontStyle.Normal
                )
            )
        }

        ButtonsRow(up =up, down = down)
    }
}