package aci.personal.piggybank.ui.composables.common.atoms.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun ArrowBackIcon(onUpClick: () -> Unit) {
    IconButton(onClick = onUpClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Go to previous Screen"
        )
    }
}
