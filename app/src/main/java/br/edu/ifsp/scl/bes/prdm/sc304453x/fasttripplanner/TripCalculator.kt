package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner

object TripCalculator {
    fun calculateTotal(
        days: Int,
        dailyBudget: Double,
        accommodationType: AccommodationType,
        hasTransport: Boolean,
        hasFood: Boolean,
        hasTours: Boolean
    ): Double {
        val baseCost = days * dailyBudget
        val accommodationCost = baseCost * accommodationType.multiplier

        val transportCost = if (hasTransport) 300.0 else 0.0
        val foodCost = if (hasFood) 50.0 * days else 0.0
        val toursCost = if (hasTours) 120.0 * days else 0.0

        return accommodationCost + transportCost + foodCost + toursCost
    }
}