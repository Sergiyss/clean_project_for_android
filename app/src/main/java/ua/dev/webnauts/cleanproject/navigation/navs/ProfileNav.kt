package ua.dev.webnauts.cleanproject.navigation.navs


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.utils.createTransitionComposableArg

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(Graph.Profile.graph, navOptions)
}
fun NavGraphBuilder.profileNav(appState: AppState) {
    navigation(
        route = Graph.Profile.graph,
        startDestination = NavRoutes.Profile.route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.Profile.route,
        ){
           // ProfileScreen()
        }
    }
}