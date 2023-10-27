package aci.personal.piggybank.ui.composables.navbars.ui.view

import aci.personal.piggybank.routes.NavGraph
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { padding ->
        NavGraph(navController = navController, padding = padding)
    }
}
