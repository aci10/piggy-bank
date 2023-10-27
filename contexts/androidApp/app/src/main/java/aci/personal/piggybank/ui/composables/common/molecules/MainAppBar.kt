package aci.personal.piggybank.ui.composables.common.molecules

import aci.personal.piggybank.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            MainAppBarAction(
                onClick = { /*TODO*/ },
                image = Icons.Default.Search
            )
            MainAppBarAction(
                onClick = { /*TODO*/ },
                image = Icons.Default.Share
            )
        }
    )
}

@Composable
private fun MainAppBarAction(onClick: () -> Unit, image: ImageVector) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = image,
            contentDescription = null
        )
    }
}
