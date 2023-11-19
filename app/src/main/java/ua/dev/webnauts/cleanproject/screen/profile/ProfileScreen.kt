package ua.dev.webnauts.cleanproject.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.database.UserDatabaseManagement
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel
import ua.dev.webnauts.cleanproject.ui.compose_components.Button.AppButton
import ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars.DefaultTopBar
import ua.dev.webnauts.cleanproject.ui.theme.customShapes
import ua.dev.webnauts.cleanproject.ui.theme.spacing

@Composable
fun ProfileScreen(
    appState: AppState,
    profileViewModel: ProfileViewModel = hiltViewModel() ){

    val user by remember { mutableStateOf(UserDatabaseManagement.userProfile) }

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Registration Screen",
                onClick = { appState.navController.navigateUp() }
            )
        }) {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = MaterialTheme.spacing.medium)){
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top,
            ) {
                Surface(
                    modifier = Modifier,
                    shape = MaterialTheme.customShapes.half,
                    color = Color.White,
                    elevation = 6.dp
                ) {
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(id = R.drawable.test_image),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }

                Column {
                    Text(text = "Name: ${user?.user?.fullName}", style = MaterialTheme.typography.body1)
                    Text(text = "Email: ${user?.user?.email}", style = MaterialTheme.typography.body1)
                    Text(text = "Phone: ${user?.user?.phone}", style = MaterialTheme.typography.body1)
                }
            }


            AppButton(text = "Exit") {

                profileViewModel.exitUser(
                    user?.user?.email!!,
                ){
                    appState.navController.navigate(Graph.Login.graph){
                        launchSingleTop = true
                    }
                }
            }

//            Button(onClick = {
//                appState.navController.navigate(Graph.Login.graph)
//            }) {
//                Text(text = "Go to Registration")
//            }
        }
    }
}