package aci.personal.piggybank.routes

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavRoute(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Home : NavRoute("home")

    // Moneyboxes Section
    object Moneyboxes : NavRoute("moneyboxes")
    object MoneyboxesList : NavRoute("moneyboxes_list")
    object MoneyboxDetail : NavRoute("moneybox_detail")
    object MoneyboxObjectives : NavRoute("moneybox_objectives")
    object MoneyboxHistory : NavRoute("moneybox_history")
    object MoneyboxAddExpenseForm : NavRoute("moneybox_add_expense")

    object User : NavRoute("user")
    object Settings : NavRoute("settings")
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    MoneyboxId(key = "moneyboxId", NavType.StringType)
}
