package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.AccommodationType

@Composable
fun TripOptionScreen(
    modifier: Modifier = Modifier,
    onCalculateClick: (String, Int, Double) -> Unit,
    onReturnClick: (String, Int, Double) -> Unit
) {
    var accommodationSelected by rememberSaveable { mutableStateOf(AccommodationType.ECONOMIC.name) }
    var hasTransport by rememberSaveable {mutableStateOf(false)}
    var hasFood by rememberSaveable {mutableStateOf(false)}
    var hasTours by rememberSaveable { mutableStateOf(false)}

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Escolha seu tipo de hospedagem")
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

        Text("Escolha os seus serviços")

        Row(
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Checkbox(
                checked = hasTransport,
                onCheckedChange = { hasTransport = it}
            )
            Text("Transporte")
        }

        Row(
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Checkbox(
                checked = hasFood,
                onCheckedChange = { hasFood = it}
            )
            Text("Alimentação")
        }

        Row(
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Checkbox(
                checked = hasTours,
                onCheckedChange = { hasTours = it}
            )
            Text("Passeios")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripOptionsScreenPreview() {
    TripOptionScreen(onCalculateClick = {_, _, _ ->}, onReturnClick = {_, _, _ ->})
}