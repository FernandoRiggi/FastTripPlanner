package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable.TripResumeScreen
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.theme.FastTripPlannerTheme

class TripSummaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recupera todos os dados enviados pelas telas anteriores.
        val destination = intent.getStringExtra(TripIntentKeys.DESTINATION) ?: ""
        val days = intent.getIntExtra(TripIntentKeys.DAYS, 0)
        val dailyBudget = intent.getDoubleExtra(TripIntentKeys.DAILY_BUDGET, 0.0)

        // Converte a String recebida pelo Intent novamente para o enum usado no cálculo e na UI.
        val accommodationName = intent.getStringExtra(TripIntentKeys.ACCOMMODATION)
            ?: AccommodationType.ECONOMIC.name

        val accommodationType = AccommodationType.valueOf(accommodationName)

        val hasTransport = intent.getBooleanExtra(TripIntentKeys.HAS_TRANSPORT, false)
        val hasFood = intent.getBooleanExtra(TripIntentKeys.HAS_FOOD, false)
        val hasTours = intent.getBooleanExtra(TripIntentKeys.HAS_TOURS, false)

        // Calcula o valor final antes de renderizar a tela de resumo.
        val totalPrice = TripCalculator.calculateTotal(
            days,
            dailyBudget,
            accommodationType,
            hasTransport,
            hasFood,
            hasTours
        )

        enableEdgeToEdge()
        setContent {
            FastTripPlannerTheme {
                TripResumeScreen(
                    destination = destination,
                    days = days,
                    dailyBudget = dailyBudget,
                    accommodationType = accommodationType,
                    hasTransport = hasTransport,
                    hasFood = hasFood,
                    hasTours = hasTours,
                    totalPrice = totalPrice,
                    onRestartClick = {
                        // Limpa as telas anteriores da pilha para reiniciar o planejamento do começo.
                        val restartIntent = Intent(
                            this@TripSummaryActivity,
                            MainActivity::class.java
                        ).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        }

                        startActivity(restartIntent)
                        finish()
                    }
                )
            }
        }
    }
}