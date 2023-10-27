package aci.personal.piggybank.ui.composables.screens.moneboxes.view

import aci.personal.piggybank.domain.moneybox.model.Moneybox
import aci.personal.piggybank.ui.composables.screens.moneboxes.viewmodel.MoneyboxViewModel
import aci.personal.piggybank.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun MoneyboxesList(
    modifier: Modifier,
    viewModel: MoneyboxViewModel,
    onCardClick: (Moneybox) -> Unit
) {
    val moneyboxes: List<Moneybox> by viewModel.moneyboxes.collectAsState()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(MaterialTheme.spacing.tiny)
    ) {
        items(moneyboxes.size) { index ->
            val item = moneyboxes[index]
            MoneyboxItem(
                moneybox = item,
                modifier = Modifier,
                onClick = { onCardClick(item) }
            )
        }
    }
}
