package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TripDataScreen(
    modifier: Modifier = Modifier,
    onAdvanceClick: (String, Int, Double) -> Unit
) {
    var destination by rememberSaveable { mutableStateOf("")}
    var days by rememberSaveable { mutableStateOf("")}
    var dailyBudget by rememberSaveable { mutableStateOf("")}

    var errorMessage by rememberSaveable {mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = destination,
            onValueChange = { destination = it },
            label = { Text("Destino") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = days,
            onValueChange = { days = it },
            label = { Text("Número de dias") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dailyBudget,
            onValueChange = { dailyBudget = it },
            label = { Text("Orçamento diário") },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotBlank()) {
            Text(text = errorMessage)
        }

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

@Preview(showBackground = true)
@Composable
fun TripDataScreenPreview() {
    TripDataScreen(onAdvanceClick = { _, _, _ ->})
}