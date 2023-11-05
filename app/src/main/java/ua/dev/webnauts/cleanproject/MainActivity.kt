package ua.dev.webnauts.cleanproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import dagger.hilt.android.AndroidEntryPoint
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Navigation
import ua.dev.webnauts.cleanproject.network.network_monitor.NetworkMonitor
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
                val appState  = rememberAppState(
                    networkMonitor = networkMonitor
                )

                var selectedItem by remember { mutableStateOf(0) }
                val items = listOf("Songs", "Artists", "Profiles")
                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                println("<<<<< screen: ${currentDestination?.route}")
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                                    label = { androidx.compose.material.Text(item) },
                                    selected = selectedItem == index,
                                    onClick = { selectedItem = index }
                                )
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
                            appState = rememberAppState(
                                networkMonitor = networkMonitor
                            ), startDestination = "", token = "", loginViewModel
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CleanProjectTheme {
        Greeting("Android")
    }
}