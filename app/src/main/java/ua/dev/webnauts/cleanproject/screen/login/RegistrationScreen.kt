package ua.dev.webnauts.cleanproject.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.ui.compose_components.Button.AppButton
import ua.dev.webnauts.cleanproject.ui.compose_components.TextField.TextFieldWithTitle
import ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars.DefaultTopBar
import ua.dev.webnauts.cleanproject.ui.theme.spacing

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
            modifier = Modifier.fillMaxSize(1f).verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .align(Alignment.Center)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.Top),
            ) {
                Column {
                    TextFieldWithTitle(
                        title = "First Name",
                        value = firstName,
                        onValueChange = {
                            firstName = it
                        }
                    )
                }
                Column {
                    TextFieldWithTitle(
                        title = "Last Name",
                        value = lastName,
                        onValueChange = {
                            lastName = it
                        }
                    )
                }
                Column {
                    TextFieldWithTitle(
                        title = "Email",
                        value = email,
                        onValueChange = {
                            email = it
                        }
                    )
                }
                Column {
                    TextFieldWithTitle(
                        title = "Password",
                        value = password,
                        onValueChange = {
                            password = it
                        }
                    )
                }
                Column {
                    TextFieldWithTitle(
                        title = "Confirm Password",
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                        }
                    )

                }

                AppButton(text = "Register") {
                    viewModel.registerUser(
                        email = email,
                        firsName = firstName,
                        lastName = lastName
                    ){
                        appState.navigateToTopLevelDestination(Graph.Home)
                    }
                }
            }
        }
    }
}