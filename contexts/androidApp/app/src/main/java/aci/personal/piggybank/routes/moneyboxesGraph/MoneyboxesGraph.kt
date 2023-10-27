package aci.personal.piggybank.routes.moneyboxesGraph

import aci.personal.piggybank.routes.NavRoute
import aci.personal.piggybank.routes.composable
import aci.personal.piggybank.routes.sharedViewModel
import aci.personal.piggybank.ui.composables.screens.moneboxes.view.MoneyboxDetail
import aci.personal.piggybank.ui.composables.screens.moneboxes.view.MoneyboxesScreen
import aci.personal.piggybank.ui.composables.screens.moneboxes.viewmodel.MoneyboxViewModel
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

fun NavGraphBuilder.MoneyboxesGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    navigation(
        route = NavRoute.Moneyboxes.route,
        startDestination = NavRoute.MoneyboxesList.route
    ) {
        MoneyboxListGraph(navController, padding)

        MoneyboxDetailGraph(navController, padding)

        composable(NavRoute.MoneyboxAddExpenseForm) {
            val viewModel = it.sharedViewModel<MoneyboxViewModel>(navController)
        }
        composable(NavRoute.MoneyboxObjectives) {
            val viewModel = it.sharedViewModel<MoneyboxViewModel>(navController)
        }
        composable(NavRoute.MoneyboxHistory) {
            val viewModel = it.sharedViewModel<MoneyboxViewModel>(navController)
        }
    }
}

private fun NavGraphBuilder.MoneyboxListGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    composable(
        navRoute = NavRoute.MoneyboxesList,
        exitTransition = {
            when (targetState.destination.route) {
                NavRoute.MoneyboxDetail.route -> {
                    slideOutHorizontally(
                        targetOffsetX = { -300 },
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
                else -> {
                    fadeOut(tween(300))
                }
            }
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            )
        }
    ) {
        val viewModel = it.sharedViewModel<MoneyboxViewModel>(navController)
        MoneyboxesScreen(
            viewModel = viewModel,
            modifier = Modifier.padding(padding),
            onCardClick = { moneybox ->
                viewModel.selectMoneybox(moneybox.id)
                navController.navigate(NavRoute.MoneyboxDetail.route)
            },
            onFABClick = {
                navController.navigate(
                    NavRoute.MoneyboxAddExpenseForm.route
                )
            }
        )
    }
}

private fun NavGraphBuilder.MoneyboxDetailGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    composable(
        navRoute = NavRoute.MoneyboxDetail,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            )
        }
    ) {
        val viewModel = it.sharedViewModel<MoneyboxViewModel>(navController)
        MoneyboxDetail(
            modifier = Modifier.padding(padding),
            viewModel = viewModel,
            onUpClick = { navController.popBackStack() }
        )
    }
}
