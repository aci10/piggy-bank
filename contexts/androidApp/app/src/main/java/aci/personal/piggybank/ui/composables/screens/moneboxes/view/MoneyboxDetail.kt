package aci.personal.piggybank.ui.composables.screens.moneboxes.view

import aci.personal.piggybank.domain.moneybox.model.Moneybox
import aci.personal.piggybank.ui.composables.common.atoms.icons.ArrowBackIcon
import aci.personal.piggybank.ui.composables.screens.moneboxes.viewmodel.MoneyboxViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyboxDetail(modifier: Modifier, viewModel: MoneyboxViewModel, onUpClick: () -> Unit) {
    val moneybox: Moneybox? by viewModel.selectedMoneybox.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = moneybox?.name?.value ?: "Selected moneybox")
                },
                navigationIcon = { ArrowBackIcon(onUpClick) }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Text(text = moneybox?.name?.value ?: "NaN")
        }
    }
}
