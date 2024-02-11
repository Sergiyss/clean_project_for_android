package ua.dev.webnauts.cleanproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.conena.nanokt.android.util.logDebug
import com.conena.nanokt.android.util.logError
import com.conena.nanokt.android.util.logWarn
import dagger.hilt.android.AndroidEntryPoint
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
import ua.dev.webnauts.cleanproject.navigation.Navigation
import ua.dev.webnauts.cleanproject.network.network_monitor.NetworkMonitor
import ua.dev.webnauts.cleanproject.screen.home.components.navigationData
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel
import ua.dev.webnauts.cleanproject.screen.welcome.Welcome
import ua.dev.webnauts.cleanproject.ui.theme.CleanProjectTheme
import ua.dev.webnauts.cleanproject.utils.animation.navanimation.enterTransitionHorizontally
import ua.dev.webnauts.cleanproject.utils.animation.navanimation.exitTransitionHorizontally
import javax.inject.Inject
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import ua.dev.webnauts.cleanproject.auth.google.authGoogleLauncher

/***
 *  Проект с одним модулем...
 *
 *  Для быстрого старта, где уже есть всё необходимое, чтобы начать программировать
 *  и накидывать дизайн, не отвлекаясь на подключение либ и настройки проекта, навигации
 * */


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor


    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel: LoginViewModel by viewModels()
        val registrationViewModel: RegistrationViewModel by viewModels()



        setContent {
            val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

            var startScreen  by remember { mutableStateOf<String?>( null ) }
            val animationState = remember { MutableTransitionState(false) }
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                registrationViewModel.logoutGoogle(context)
            }


            val authResultLauncher = authGoogleLauncher(registrationViewModel.googleSignInOptions) { task ->

                println("registrationViewModel $task > ${registrationViewModel.googleSignInOptions}")

                registrationViewModel.isResultGoogle(task,
                    isRegisterGoogle = { isRegister, token, googleSignInAccount ->
                        val name = googleSignInAccount.displayName?.split(" ")
                        //Если ошибка регистрации, то переходим на экран регистрации
                        println("registrationViewModel $name")
                        println("registrationViewModel ${googleSignInAccount.email}")
                    }, isLoginGoogle = {
                        println("registrationViewModel login google ")
                    }
                )
            }


            CleanProjectTheme {
//                Welcome(route = { startRoute->
//                    startScreen = startRoute
//                    animationState.targetState = true
//                })


                Column {
                    Button(
                        onClick = {
                            authResultLauncher.launch(333)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(

                            contentColor = Color.White
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = ""
                        )
                        Text(text = "Sign in with Google", modifier = Modifier.padding(6.dp))
                    }

                }

//                startScreen?.let { destination->
//
//                    if (!permissionState.status.isGranted && permissionState.status.shouldShowRationale) {
//                        //Если наданы разрешения
//                    }else{
//
//                        LaunchedEffect(key1 = Unit, block = { permissionState.launchPermissionRequest() })
//                    }
//
//
//                    AnimatedVisibility(
//                        visibleState = animationState,
//                        enter = enterTransitionHorizontally(3000, 1000),
//                        exit = exitTransitionHorizontally(3000, 1000)
//                    ) {
//
//                        RootScreen(
//                            loginViewModel = loginViewModel,
//                            networkMonitor = networkMonitor,
//                            startDestination = destination
//                        )
//                    }
//                }
            }
        }
    }
}

/***
 * Стартовая точка для [rootNavigation]
 * Здесь будут всё основные экраны. Отделил по той причине, что хочу [WelcomeScreen]
 * вынести из навигации вверх. Во многих моментах она будет только мешать.
 * */
@Composable
fun RootScreen(loginViewModel: LoginViewModel, networkMonitor: NetworkMonitor, startDestination : String){
    val appState  = rememberAppState(networkMonitor = networkMonitor,)
    var selectedItem by remember { mutableStateOf(0) }


    appState.showSnackBar("hi", duration = androidx.compose.material.SnackbarDuration.Short)


    val currentDestination =  appState.currentDestination


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
             * @param token - token for user,
             * @param startDestination start Screen
             * */
            Navigation(
                appState = appState,
                loginViewModel = loginViewModel,
                startDestination = startDestination
            )

        }
    }
}