package ua.dev.webnauts.cleanproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
import ua.dev.webnauts.cleanproject.navigation.Navigation
import ua.dev.webnauts.cleanproject.network.network_monitor.NetworkMonitor
import ua.dev.webnauts.cleanproject.screen.home.components.navigationData
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel
import ua.dev.webnauts.cleanproject.ui.theme.CleanProjectTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel: LoginViewModel by viewModels()

        setContent {
            CleanProjectTheme {
                val appState  = rememberAppState(networkMonitor = networkMonitor,)
                var selectedItem by remember { mutableStateOf(0) }


                appState.showSnackBar("hi", duration = androidx.compose.material.SnackbarDuration.Short)


                val currentDestination =  appState.currentDestination


                println("<<<<< screen: ${currentDestination?.route}")
                Scaffold(
                    bottomBar = {
                        if (currentDestination?.route != NavRoutes.Welcome.route &&
                            currentDestination?.route != NavRoutes.Login.route &&
                            currentDestination?.route != NavRoutes.RegisterScreen().route) {
                            NavigationBar {
                                navigationData.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        icon = {
                                            androidx.compose.material3.Icon(
                                                imageVector = item.icon,
                                                contentDescription = item.title,
                                            )
                                        },
                                        label = { androidx.compose.material.Text(item.title) },
                                        selected = selectedItem == index,
                                        onClick = {
                                            selectedItem = index
                                            appState.navigateToTopLevelDestination(item.route)
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) {
                    Column(Modifier.padding(it)) {

                        /**
                         * @param startDestination - start destination for navigation
                         * @param token - token for user
                         * */
                        Navigation(
                            appState = appState,
                            loginViewModel
                        )

                    }
                }
            }
        }
    }
}
