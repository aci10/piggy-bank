package aci.personal.piggybank.ui.composables.screens.moneboxes.view

import aci.personal.piggybank.R
import aci.personal.piggybank.domain.moneybox.model.Moneybox
import aci.personal.piggybank.ui.composables.screens.moneboxes.viewmodel.MoneyboxViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp

@Composable
fun MoneyboxesList(viewModel: MoneyboxViewModel) {
    val moneyboxes: List<Moneybox> by viewModel.moneyboxes.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_xs))
    ) {
        items(moneyboxes.size) { index ->
            val item = moneyboxes[index]
            MoneyboxItem(item)
        }
    }
}

// @Preview(showBackground = true)
@Composable
fun MoneyboxItem(moneybox: Moneybox) {
    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = moneybox.name.value,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = moneybox.funds.value.toString(),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
