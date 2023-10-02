package aci.personal.piggybank.routes

import aci.personal.piggybank.ui.composables.caldendar.ui.view.UserScreen
import aci.personal.piggybank.ui.composables.screens.home.view.HomeScreen
import aci.personal.piggybank.ui.composables.screens.moneboxes.view.MoneyboxesScreen
import aci.personal.piggybank.ui.composables.screens.settings.view.SettingsScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavBarItem.Home.route
    ) {
        composable(BottomNavBarItem.Home) {
            HomeScreen()
        }
        composable(BottomNavBarItem.Moneyboxes) {
            MoneyboxesScreen(hiltViewModel())
        }
        composable(BottomNavBarItem.User) {
            UserScreen()
        }
        composable(BottomNavBarItem.Settings) {
            SettingsScreen()
        }
    }
}

private fun NavGraphBuilder.composable(
    bottomNavBarItem: BottomNavBarItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = bottomNavBarItem.route,
        arguments = bottomNavBarItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value =
        when (arg.navType) {
            NavType.IntType -> {
                arguments?.getInt(arg.key)
            }
            NavType.IntArrayType -> {
                arguments?.getIntArray(arg.key)
            }
            NavType.LongType -> {
                arguments?.getLong(arg.key)
            }
            NavType.LongArrayType -> {
                arguments?.getLongArray(arg.key)
            }
            NavType.BoolType -> {
                arguments?.getBoolean(arg.key)
            }
            NavType.BoolArrayType -> {
                arguments?.getBooleanArray(arg.key)
            }
            NavType.StringType -> {
                arguments?.getString(arg.key)
            }
            NavType.StringArrayType -> {
                arguments?.getStringArray(arg.key)
            }
            NavType.FloatType -> {
                arguments?.getFloat(arg.key)
            }
            NavType.FloatArrayType -> {
                arguments?.getFloatArray(arg.key)
            }
            else -> { null }
        }

    requireNotNull(value)

    return value as T
}

@Composable
fun <T> NavBackStackEntry.sharedViewModel(navController: NavHostController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
