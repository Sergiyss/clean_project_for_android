package ua.dev.webnauts.cleanproject.screen.base_creen.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.dev.webnauts.cleanproject.R


@Preview
@Composable
fun ButtonsRow(up:()-> Unit = {}, down:()-> Unit = {}){
    var upEnabled  by remember { mutableStateOf( true ) }
    var downEnabled  by remember { mutableStateOf( true ) }

    Row(
        modifier = Modifier.fillMaxWidth(1f),
        horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Button(
            modifier = Modifier.weight(1f),
            shape =  RoundedCornerShape(10.dp),
            enabled = upEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF47A60C),
                disabledContainerColor = Color(0xFF47A60C).copy(alpha = .6f)
            ),
            onClick = {
                upEnabled = upEnabled.not()
                downEnabled = downEnabled.not()
                up()
            }) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Вверх", style = TextStyle(
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    fontStyle = FontStyle.Normal
                )
                )
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.up),
                    contentDescription = "Up", tint = if(upEnabled) Color.White else Color.White.copy(.6f)
                )
            }
        }

        Button(
            modifier = Modifier.weight(1f),
            shape =  RoundedCornerShape(10.dp),
            enabled= downEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFA6310C),
                disabledContainerColor = Color(0xFFA6310C).copy(alpha = .6f)
            ),
            onClick = {
                upEnabled = upEnabled.not()
                downEnabled = downEnabled.not()
                down()
            }) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Вниз", style = TextStyle(
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        lineHeight = 19.sp,
                        fontStyle = FontStyle.Normal
                    )
                )
                Icon(
                    modifier = Modifier.rotate(180f).size(16.dp),
                    painter = painterResource(id = R.drawable.up),
                    contentDescription = "Up", tint = if(downEnabled) Color.White else Color.White.copy(.6f)
                )
            }
        }
    }
}