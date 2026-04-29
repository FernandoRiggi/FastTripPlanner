package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.AccommodationType

@Composable
fun TripOptionScreen(
    modifier: Modifier = Modifier,
    onCalculateClick: (String, Int, Double) -> Unit,
    onReturnClick: (String, Int, Double) -> Unit
) {
    var accommodationSelected by rememberSaveable { mutableStateOf(AccommodationType.ECONOMIC.name) }
    var servicesType by rememberSaveable { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()) {
        AccommodationType.entries.forEach { accommodation ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        accommodationSelected = accommodation.name
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = accommodationSelected == accommodation.name,
                    onClick = {
                        accommodationSelected = accommodation.name
                    }
                )

                Text(accommodation.label)
            }
    }
}
}

@Preview(showBackground = true)
@Composable
fun TripOptionsScreenPreview() {
    TripOptionScreen(onCalculateClick = {_, _, _ ->}, onReturnClick = {_, _, _ ->})
}