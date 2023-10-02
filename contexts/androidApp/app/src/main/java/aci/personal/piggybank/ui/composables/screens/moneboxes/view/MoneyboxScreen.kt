package aci.personal.piggybank.ui.composables.screens.moneboxes.view

import aci.personal.piggybank.R
import aci.personal.piggybank.ui.composables.screens.moneboxes.viewmodel.MoneyboxViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource

@Composable
fun MoneyboxesScreen(viewModel: MoneyboxViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_md))
        ) {
            MoneyboxesList(viewModel)
        }
    }
}
