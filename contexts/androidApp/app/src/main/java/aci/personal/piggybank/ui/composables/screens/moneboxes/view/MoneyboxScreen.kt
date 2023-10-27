package aci.personal.piggybank.ui.composables.screens.moneboxes.view

import aci.personal.piggybank.domain.moneybox.model.Moneybox
import aci.personal.piggybank.ui.composables.common.molecules.MainAppBar
import aci.personal.piggybank.ui.composables.screens.moneboxes.viewmodel.MoneyboxViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MoneyboxesScreen(
    viewModel: MoneyboxViewModel,
    modifier: Modifier,
    onCardClick: (Moneybox) -> Unit,
    onFABClick: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MainAppBar() },
        floatingActionButton = { FAB(onFABClick) },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        MoneyboxesList(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            viewModel = viewModel,
            onCardClick = onCardClick
        )
    }
}

@Composable
fun FAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick() }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
    }
}
