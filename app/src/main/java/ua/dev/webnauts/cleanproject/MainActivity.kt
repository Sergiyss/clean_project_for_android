package ua.dev.webnauts.cleanproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column {

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