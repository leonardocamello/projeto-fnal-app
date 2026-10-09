package br.edu.ifpe.agroplay.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.edu.ifpe.agroplay.ui.InventoryViewModel
import br.edu.ifpe.agroplay.ui.screens.*

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    viewModel: InventoryViewModel,
    onShowSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                viewModel = viewModel,
                onNavigateToFarmList = { navController.navigate("farm_list") },
                onLoginSuccess = { navController.navigate("inventory_list") },
                onShowSnackbar = onShowSnackbar
            )
        }
        composable("farm_list") {
            FarmListScreen(
                viewModel = viewModel,
                onNavigateToLogin = { navController.navigate("login") },
                onFarmSelected = { navController.navigate("inventory_list") }
            )
        }
        composable("inventory_list") {
            InventoryListScreen(
                viewModel = viewModel,
                onNavigateToItemEntry = { navController.navigate("item_entry") },
                onNavigateToItemUpdate = { itemId ->
                    navController.navigate("item_edit/$itemId")
                },
                onNavigateToFarmList = { navController.navigate("farm_list") }
            )
        }
        composable("item_entry") {
            ItemEntryScreen(
                viewModel = viewModel,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                onShowSnackbar = onShowSnackbar
            )
        }
        composable(
            route = "item_edit/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            ItemEditScreen(
                itemId = itemId,
                viewModel = viewModel,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                onShowSnackbar = onShowSnackbar
            )
        }
    }
}
