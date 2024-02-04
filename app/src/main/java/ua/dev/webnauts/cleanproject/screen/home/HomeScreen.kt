package ua.dev.webnauts.cleanproject.screen.home


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel
import ua.dev.webnauts.cleanproject.ui.compose_components.photo_text_field.Country
import ua.dev.webnauts.cleanproject.ui.compose_components.photo_text_field.PhoneTextField

@Composable
fun HomeScreen(
    appState: AppState,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    var animated by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val rotation = remember { Animatable(initialValue = 0f) }
    val scale = remember { Animatable(initialValue = 1f) }
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Profiles")

    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var phoneNumber  by remember { mutableStateOf( TextFieldValue() ) }

    var test  by remember { mutableStateOf( "Home Screen" ) }

    LaunchedEffect(animated) {
        // if(animated == false ) return@LaunchedEffect
        rotation.animateTo(
            targetValue = if (animated) 180f else 0f,
            animationSpec = tween(durationMillis = 1000),
        )

        scale.animateTo(
            targetValue = if (animated) 3f else 1f,
            animationSpec = tween(durationMillis = 1000),
        )
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUseFlow()?.collect {
            test = it.lastOrNull()?.usersData.toString()
        }
    })

    println("<<<<< screen: 3-> ${currentDestination?.route}")

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = {
                viewModel.dataBase.databaseQueries.insertUser(
                    1, "kkkk", "llllll 000 asdasd"
                )
            }) {
                Text("New Text")
            }
            PhoneTextField(
                value = phoneNumber,
                context = context,
                onValueChange = {
                    phone, country ->
                    phoneNumber = phone
                },
                onCountryChange = {
                        phone, country ->
                    phoneNumber = phone
                },
                countries = listOf(Country(99,
                    "ru", "+380" ))
            )


            LazyColumn(content = {
                items(100) {
                    Text(text = "Item $it", Modifier.padding(10.dp))
                }
            })
        }
    }

}

