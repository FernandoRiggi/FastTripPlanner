package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.composable.TripDataScreen
import br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner.ui.theme.FastTripPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FastTripPlannerTheme {
                TripDataScreen(
                    onAdvanceClick = { destination, days, dailyBudget ->
                        // Intent explícita para abrir a segunda tela do fluxo.
                        val intent = Intent(
                            this@MainActivity,
                            TripOptionsActivity::class.java
                        ).apply {
                            // Dados digitados na primeira tela são enviados para a tela de opções.
                            putExtra(TripIntentKeys.DESTINATION, destination)
                            putExtra(TripIntentKeys.DAYS, days)
                            putExtra(TripIntentKeys.DAILY_BUDGET, dailyBudget)
                        }

                        startActivity(intent)
                    }
                )
            }
        }
    }
}