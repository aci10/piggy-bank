package aci.personal.piggybank.ui.composables.navbars.ui.view

import aci.personal.piggybank.routes.BottomNavGraph
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) {
        Modifier.padding(it)
        BottomNavGraph(navController = navController)
    }
}
