package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner

// Responsável por concentrar a regra de cálculo da viagem fora das telas.
// Isso deixa as Activities e os Composables focados apenas em navegação e interface.
object TripCalculator {
    fun calculateTotal(
        days: Int,
        dailyBudget: Double,
        accommodationType: AccommodationType,
        hasTransport: Boolean,
        hasFood: Boolean,
        hasTours: Boolean,
        hasEconomicMode: Boolean
    ): Double {
        var dailyBudgetCalculated = dailyBudget
        if (hasEconomicMode) {
            dailyBudgetCalculated = dailyBudget * 0.85
        }
        // Custo base definido no enunciado: número de dias vezes orçamento diário.
        val baseCost = days * dailyBudgetCalculated

        // O custo da hospedagem varia conforme o multiplicador da opção selecionada.
        val accommodationCost = baseCost * accommodationType.multiplier

        // Serviços adicionais são somados apenas quando foram selecionados pelo usuário.
        val transportCost = if (hasTransport) 300.0 else 0.0
        val foodCost = if (hasFood) 50.0 * days else 0.0
        val toursCost = if (hasTours) 120.0 * days else 0.0

        return accommodationCost + transportCost + foodCost + toursCost
    }
}