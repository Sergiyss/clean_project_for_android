package ua.dev.webnauts.cleanproject.navigation.navs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.screen.tab_two.TabsTwoScreen
import ua.dev.webnauts.cleanproject.utils.createTransitionComposableArg

fun NavController.navigateToTab(navOptions: NavOptions? = null) {
    this.navigate(Graph.TabTwo.graph, navOptions)
}
fun NavGraphBuilder.tabNav(appState: AppState) {
    navigation(
        route = Graph.TabTwo.graph,
        startDestination = NavRoutes.TabTwo.route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.TabTwo.route,
        ){
            TabsTwoScreen(appState = appState)
        }
    }
}