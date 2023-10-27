package aci.personal.piggybank.routes

import aci.personal.piggybank.routes.moneyboxesGraph.MoneyboxesGraph
import aci.personal.piggybank.ui.composables.caldendar.ui.view.UserScreen
import aci.personal.piggybank.ui.composables.screens.home.view.HomeScreen
import aci.personal.piggybank.ui.composables.screens.settings.view.SettingsScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {
        composable(NavRoute.Home) {
            HomeScreen()
        }

        MoneyboxesGraph(navController, padding)

        composable(NavRoute.User) {
            UserScreen()
        }
        composable(NavRoute.Settings) {
            SettingsScreen()
        }
    }
}

fun NavGraphBuilder.composable(
    navRoute: NavRoute,
    enterTransition: (
        @JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? = null,
    exitTransition: (
        @JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
    )? = null,
    popEnterTransition: (
        @JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? =
        enterTransition,
    popExitTransition: (
        @JvmSuppressWildcards
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
    )? =
        exitTransition,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navRoute.route,
        arguments = navRoute.args,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        content(it)
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
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
