package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.AccommodationType

@Composable
fun TripResumeScreen(
    modifier: Modifier = Modifier,
    destination: String,
    days: Int,
    dailyBudget: Double,
    accommodationType: AccommodationType,
    hasTransport: Boolean,
    hasFood: Boolean,
    hasTours: Boolean,
    totalPrice: Double,
    onRestartClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Resumo da Viagem")
        Text("Destino: $destination")
        Text("Número de dias: $days")
        Text("Orçamento diário: R$$dailyBudget")
        Text("Hospedagem: ${accommodationType.label}")
        Text("Transporte: ${if (hasTransport) "Sim" else "Não"}")
        Text("Comida: ${if (hasFood) "Sim" else "Não"}")
        Text("Passeios: ${if(hasTours) "Sim" else "Não"}")
        Text("Total: R$$totalPrice")
    }
}

@Preview(showBackground = true)
@Composable
fun TripResumeScreenPreview() {
    TripResumeScreen(
        destination = "São Paulo",
        days = 3,
        dailyBudget = 150.0,
        accommodationType = AccommodationType.COMFORT,
        hasTransport = true,
        hasFood = true,
        hasTours = false,
        totalPrice = 1350.0,
        onRestartClick = {}
    )
}
