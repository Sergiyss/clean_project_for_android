package ua.dev.webnauts.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.dev.webnauts.cleanproject.ui.compose_components.Button.AppButton

@Composable
fun ProfileScreen() {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {

            Column() {
                Text(text = "Name: user?.user?.fullName", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Email:user?.user?.email", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Phone: user?.user?.phone", style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.weight(1f))
            AppButton(text = "Exit") {
                
            }
        }
    }
}