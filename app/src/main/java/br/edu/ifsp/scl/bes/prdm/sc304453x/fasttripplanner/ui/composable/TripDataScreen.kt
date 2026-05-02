package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.theme.FastTripPlannerTheme

@Composable
fun TripDataScreen(
    modifier: Modifier = Modifier,
    onAdvanceClick: (String, Int, Double) -> Unit
) {
    var destination by rememberSaveable { mutableStateOf("")}
    var days by rememberSaveable { mutableStateOf("")}
    var dailyBudget by rememberSaveable { mutableStateOf("")}

    var errorMessage by rememberSaveable {mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Dados da Viagem", fontSize = 26.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = destination,
                onValueChange = { destination = it },
                label = { Text("Destino") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = days,
                onValueChange = { days = it },
                label = { Text("Número de dias") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = dailyBudget,
                onValueChange = { dailyBudget = it },
                label = { Text("Orçamento diário") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                )
            )

            if (errorMessage.isNotBlank()) {
                Text(text = errorMessage)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val numberOfDays = days.toIntOrNull()
                    val dailyBudgetNumber = dailyBudget.toDoubleOrNull()

                    if (destination.isBlank()) {
                        errorMessage = "Informe o destino"
                        return@Button
                    }

                    if (numberOfDays == null || numberOfDays <= 0) {
                        errorMessage = "Informe uma quantidade de dias válida"
                        return@Button
                    }

                    if (dailyBudgetNumber == null || dailyBudgetNumber <= 0.0) {
                        errorMessage = "Informe um orçamento diário válido"
                        return@Button
                    }

                    errorMessage = ""

                    onAdvanceClick(
                        destination,
                        numberOfDays,
                        dailyBudgetNumber
                    )
                }
            ) {
                Text("Avançar")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TripDataScreenPreview() {
    FastTripPlannerTheme {
        TripDataScreen(
            onAdvanceClick = { _, _, _ -> }
        )
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TripDataScreenDarkPreview() {
    FastTripPlannerTheme {
        TripDataScreen(
            onAdvanceClick = { _, _, _ -> }
        )
    }
}