package ua.dev.webnauts.cleanproject.navigation.navs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation

import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.AppRoutes
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.screen.Screen1
import ua.dev.webnauts.cleanproject.screen.Screen2
import ua.dev.webnauts.cleanproject.screen.Screen3
import ua.dev.webnauts.cleanproject.screen.Screen4
import ua.dev.webnauts.cleanproject.screen.Screen5
import ua.dev.webnauts.cleanproject.screen.Screen6
import ua.dev.webnauts.cleanproject.screen.StartScreen

import ua.dev.webnauts.cleanproject.utils.createTransitionComposableArg



fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(Graph.Home.graph, navOptions)
}

fun NavGraphBuilder.homeNav(appState: AppState) {
    navigation(
        route = Graph.Home.graph,
        startDestination = AppRoutes.START.route
    ) {
        createTransitionComposableArg(
            route =AppRoutes.START.route,
        ){
            StartScreen(appState = appState)
        }
        createTransitionComposableArg(
            route = AppRoutes.SCREEN1.route,
        ){
            Screen1(appState = appState)
        }
        createTransitionComposableArg(
            route = AppRoutes.SCREEN2.route,
        ){
            Screen2(appState = appState)
        }
        createTransitionComposableArg(
            route = AppRoutes.SCREEN3.route,
        ){
            Screen3(appState = appState)
        }
        createTransitionComposableArg(
            route = AppRoutes.SCREEN4.route,
        ){
            Screen4(appState = appState)
        }
        createTransitionComposableArg(
            route = AppRoutes.SCREEN5.route,
        ){
            Screen5(appState = appState)
        }
        createTransitionComposableArg(
            route = AppRoutes.SCREEN6.route,
        ){
            Screen6(appState = appState)
        }
    }
}