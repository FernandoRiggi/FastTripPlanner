package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.AccommodationType
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.theme.FastTripPlannerTheme

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

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        // Mantém os dados alinhados à esquerda para facilitar a leitura do resumo.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                start = 24.dp,
                end = 24.dp,
                top = 56.dp,
                bottom = 24.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Resumo da Viagem", fontSize = 25.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Destino: $destination")
                Text("Número de dias: $days")
                Text("Orçamento diário: R$$dailyBudget")

                Spacer(modifier = Modifier.height(16.dp))

                Text("Hospedagem: ${accommodationType.label}")
                Text("Transporte: ${if (hasTransport) "Sim" else "Não"}")
                Text("Alimentação: ${if (hasFood) "Sim" else "Não"}")
                Text("Passeios: ${if(hasTours) "Sim" else "Não"}")

                Spacer(modifier = Modifier.height(16.dp))

                // Valor calculado na Activity a partir das regras de negócio do projeto.
                Text("Total: R$$totalPrice", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onRestartClick()
                    }
                ) {
                    Text("Reiniciar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripResumeScreenPreview() {
    FastTripPlannerTheme {
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
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TripResumeScreenDarkPreview() {
    FastTripPlannerTheme {
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
}