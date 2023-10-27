package aci.personal.piggybank.ui.composables.screens.home.view

import aci.personal.piggybank.ui.composables.common.molecules.MainAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainAppBar() }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(text = "Home Screen", fontSize = 20.sp)
        }
    }
}
