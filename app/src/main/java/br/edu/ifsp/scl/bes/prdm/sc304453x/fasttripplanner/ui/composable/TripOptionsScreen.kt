package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.AccommodationType
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.theme.FastTripPlannerTheme

@Composable
fun TripOptionsScreen(
    modifier: Modifier = Modifier,
    onCalculateClick: (AccommodationType, Boolean, Boolean, Boolean) -> Unit,
    onReturnClick: () -> Unit
) {
    // O estado da tela fica salvo durante rotação do dispositivo.
    var accommodationSelected by rememberSaveable { mutableStateOf(AccommodationType.ECONOMIC.name) }
    var hasTransport by rememberSaveable {mutableStateOf(false)}
    var hasFood by rememberSaveable {mutableStateOf(false)}
    var hasTours by rememberSaveable { mutableStateOf(false)}

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 56.dp,
                    bottom = 24.dp
                )
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Opções da Viagem",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Escolha seu tipo de hospedagem",
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // RadioButton é usado porque apenas um tipo de hospedagem pode ser escolhido.
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

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Escolha os seus serviços",
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Checkboxes são usados porque o usuário pode selecionar mais de um serviço.
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        hasTransport = !hasTransport
                    },
                verticalAlignment =  Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasTransport,
                    onCheckedChange = { hasTransport = it}
                )
                Text("Transporte")
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        hasFood = !hasFood
                    },
                verticalAlignment =  Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasFood,
                    onCheckedChange = { hasFood = it}
                )
                Text("Alimentação")
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        hasTours = !hasTours
                    },
                verticalAlignment =  Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasTours,
                    onCheckedChange = { hasTours = it}
                )
                Text("Passeios")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onReturnClick()
                    }
                ) {
                    Text("Voltar")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        // Converte o nome salvo no estado para o enum usado pela Activity e pelo cálculo.
                        val accommodationType = AccommodationType.valueOf(accommodationSelected)
                        onCalculateClick(
                            accommodationType,
                            hasTransport,
                            hasFood,
                            hasTours
                        )
                    }
                ) {
                    Text("Calcular")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripOptionsScreenPreview() {
    FastTripPlannerTheme {
        TripOptionsScreen(onCalculateClick = {_,_,_,_ ->}, onReturnClick = {})
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Composable
fun TripOptionsScreenDarkPreview() {
    FastTripPlannerTheme {
        TripOptionsScreen(onCalculateClick = {_,_,_,_ ->}, onReturnClick = {})
    }
}