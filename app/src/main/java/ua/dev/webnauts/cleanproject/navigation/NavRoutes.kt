package ua.dev.webnauts.cleanproject.navigation


enum class Graph(val graph : String){
    Root("root_graph"),
    Login("login_graph"),
    Home("home_graph"),
    Profile("profile_graph"),
    TabTwo("tab_two_graph")
}

sealed class NavRoutes(val route : String){
    object Welcome : NavRoutes("welcome_screen")
    object Login : NavRoutes("login_screen")
    class Home(val nav : String = "{nav}") : NavRoutes("home_screen/$nav")
    class RegisterScreen() : NavRoutes("register_screen")

    object Profile : NavRoutes("profile_screen")
    object TabTwo : NavRoutes("tab_two_screen")
}