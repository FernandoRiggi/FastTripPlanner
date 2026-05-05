package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable.TripOptionsScreen
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.theme.FastTripPlannerTheme

class TripOptionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Recupera os dados recebidos da primeira tela para repassar ao resumo.
        val destination = intent.getStringExtra(TripIntentKeys.DESTINATION) ?: ""
        val days = intent.getIntExtra(TripIntentKeys.DAYS, 0)
        val dailyBudget = intent.getDoubleExtra(TripIntentKeys.DAILY_BUDGET, 0.0)
        setContent {
            FastTripPlannerTheme {
                TripOptionsScreen(
                    onReturnClick = {
                        // Fecha a Activity atual e retorna para a tela anterior sem criar outra MainActivity.
                        finish()
                    },
                    onCalculateClick = { accommodationType, hasTransport, hasFood, hasTours ->
                        // Intent explícita para abrir a tela de resumo da viagem.
                        val intent = Intent(
                            this@TripOptionsActivity,
                            TripSummaryActivity::class.java
                        ).apply {
                            // Repassa os dados da tela 1.
                            putExtra(TripIntentKeys.DESTINATION, destination)
                            putExtra(TripIntentKeys.DAYS, days)
                            putExtra(TripIntentKeys.DAILY_BUDGET, dailyBudget)

                            // Envia as escolhas feitas na tela 2.
                            // O enum é enviado como String para simplificar a recuperação na próxima Activity.
                            putExtra(TripIntentKeys.ACCOMMODATION, accommodationType.name)
                            putExtra(TripIntentKeys.HAS_TRANSPORT, hasTransport)
                            putExtra(TripIntentKeys.HAS_FOOD, hasFood)
                            putExtra(TripIntentKeys.HAS_TOURS, hasTours)
                        }

                        startActivity(intent)
                    }

                )
            }
        }
    }
}