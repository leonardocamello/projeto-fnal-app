package br.edu.ifpe.agroplay.ui.navigation

sealed class NavTarget(val route: String) {
    object Home : NavTarget("home")
}
