package br.edu.ifsp.scl.bes.prdm.sc304453x.fasttripplanner

// Representa os tipos de hospedagem disponíveis na viagem.
// Cada opção possui um texto para exibição e um multiplicador usado no cálculo final.
enum class AccommodationType(
    val label: String,
    val multiplier: Double
) {
    ECONOMIC("Econômica", 1.0),
    COMFORT("Conforto", 1.5),
    LUXURY("Luxo", 2.2)
}