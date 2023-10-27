package aci.personal.piggybank.ui.composables.screens.moneboxes.view

import aci.personal.piggybank.domain.moneybox.model.Moneybox
import aci.personal.piggybank.ui.composables.common.atoms.charts.BarChart
import aci.personal.piggybank.ui.theme.PiggyBankTheme
import aci.personal.piggybank.ui.theme.spacing
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import java.text.DecimalFormat

@Composable
fun MoneyboxItem(moneybox: Moneybox, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Content(
            title = moneybox.name.value,
            funds = moneybox.funds.value,
            salary = moneybox.salary.value,
            saved = moneybox.saved.value
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoneyboxItemPreview() {
    PiggyBankTheme {
        Card(modifier = Modifier.fillMaxWidth()) {
            Content(
                title = "Example piggy bank name",
                funds = 1000000.10,
                salary = 50.0,
                saved = 40.0
            )
        }
    }
}

@Composable
private fun Content(title: String, funds: Double, salary: Double, saved: Double) {
    Row(
        modifier = Modifier
            .padding(MaterialTheme.spacing.extraSmall)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.small)
                .weight(0.7f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PiggyBankImg(modifier = Modifier)

            PiggyBankInfo(
                modifier = Modifier.height(80.dp),
                title = title,
                funds = funds
            )
        }

        Box(
            modifier = Modifier
                .weight(0.3f)
                .height(60.dp)
                .padding(MaterialTheme.spacing.small),
            contentAlignment = Alignment.CenterEnd
        ) {
            BarChart(
                modifier = Modifier,
                data = mapOf(
                    Pair("Salary", salary.toFloat()),
                    Pair("Saved", saved.toFloat())
                ),
                unit = "€",
                containerSize = 100.dp
            )
        }
    }
}

@Composable
private fun PiggyBankInfo(modifier: Modifier, title: String, funds: Double) {
    ConstraintLayout(
        modifier
            .padding(
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            )
    ) {
        val (titlebox, salarybox) = createRefs()
        createVerticalChain(titlebox, salarybox, chainStyle = ChainStyle.Packed)

        PiggyBankTitle(
            modifier = Modifier
                .constrainAs(titlebox) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(salarybox.bottom)
                },
            title = title
        )

        PiggyBankSalary(
            modifier = Modifier
                .constrainAs(salarybox) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(titlebox.top)
                },
            funds = funds
        )
    }
}

@Composable
private fun PiggyBankImg(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
private fun PiggyBankTitle(modifier: Modifier, title: String) {
    Box(
        modifier = modifier
            .padding(bottom = MaterialTheme.spacing.extraSmall)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun PiggyBankSalary(modifier: Modifier, funds: Double) {
    Row(
        modifier = modifier
            .padding(top = MaterialTheme.spacing.extraSmall)
    ) {
        Text(
            text = DecimalFormat.getInstance(java.util.Locale.GERMANY).format(funds),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "€",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = MaterialTheme.spacing.tiny)
        )
    }
}
