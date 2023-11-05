package ua.dev.webnauts.cleanproject.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars.DefaultTopBar

@Composable
fun RegistrationScreen(appState: AppState, viewModel: LoginViewModel = hiltViewModel()) {
    var firstName  by remember { mutableStateOf( "" ) }
    var lastName  by remember { mutableStateOf( "" ) }
    var email  by remember { mutableStateOf( "" ) }
    var password  by remember { mutableStateOf( "" ) }
    var confirmPassword  by remember { mutableStateOf( "" ) }
    var phoneNumber  by remember { mutableStateOf( "" ) }


    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Registration Screen",
                onClick = { appState.navController.navigateUp() }
            )
        }) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.Top),
            ) {
            Text(text = "Registration Screen")
                Column {
                    Text(text = "First Name")
                    TextField(value = firstName, onValueChange = {
                        firstName = it
                    })
                }
                Column {
                    Text(text = "Last Name")
                    TextField(value = lastName, onValueChange = {
                        lastName = it
                    })
                }
                Column {
                    Text(text = "Phone Number")
                    TextField(value = phoneNumber, onValueChange = {
                        phoneNumber = it
                    })
                }
                Column {
                    Text(text = "Email")
                    TextField(value = email, onValueChange = {
                        email = it
                    })
                }
                Column {
                    Text(text = "Password")
                    TextField(value = password, onValueChange = {
                        password = it
                    })
                }
                Column {

                    Text(text = "Confirm Password")
                    TextField(value = confirmPassword, onValueChange = {
                        confirmPassword = it
                    })
                }

                Button(onClick = {  }) {
                    Text(text = "Register")
                }
            }
        }
    }
}