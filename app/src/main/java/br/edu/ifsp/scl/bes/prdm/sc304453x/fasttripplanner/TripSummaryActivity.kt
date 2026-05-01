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

        val destination = intent.getStringExtra(TripIntentKeys.DESTINATION) ?: ""
        val days = intent.getIntExtra(TripIntentKeys.DAYS, 0)
        val dailyBudget = intent.getDoubleExtra(TripIntentKeys.DAILY_BUDGET, 0.0)

        val accommodationName = intent.getStringExtra(TripIntentKeys.ACCOMMODATION)
            ?: AccommodationType.ECONOMIC.name

        val accommodationType = AccommodationType.valueOf(accommodationName)

        val hasTransport = intent.getBooleanExtra(TripIntentKeys.HAS_TRANSPORT, false)
        val hasFood = intent.getBooleanExtra(TripIntentKeys.HAS_FOOD, false)
        val hasTours = intent.getBooleanExtra(TripIntentKeys.HAS_TOURS, false)

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