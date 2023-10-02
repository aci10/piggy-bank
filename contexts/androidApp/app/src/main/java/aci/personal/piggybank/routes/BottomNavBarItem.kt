package aci.personal.piggybank.routes

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class BottomNavBarItem(
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

    object Home : BottomNavBarItem("home")
    object Moneyboxes : BottomNavBarItem("moneyboxes")
    object User : BottomNavBarItem("user")
    object Settings : BottomNavBarItem("settings")
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    MoneyboxId(key = "moneyboxId", NavType.StringType)
}
