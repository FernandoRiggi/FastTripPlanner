# FastTripPlanner

Aplicativo Android desenvolvido em Kotlin para planejamento rápido de viagens.

O app permite que o usuário informe os dados básicos de uma viagem, selecione opções adicionais e visualize um resumo final com o valor total calculado.

## Objetivo

O objetivo do projeto é aplicar conceitos fundamentais de desenvolvimento Android trabalhados na disciplina, incluindo múltiplas telas, navegação entre `Activities`, envio de dados com `Intent` e preservação de estado durante mudanças de configuração, como rotação da tela.

## Tecnologias utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Intents explícitas
- Gradle Kotlin DSL
- Minimum SDK: Android 8.0, API 26

## Funcionalidades

### Tela 1 — Dados da Viagem

A primeira tela permite inserir os dados iniciais da viagem:

- destino;
- número de dias;
- orçamento diário.

A tela valida os campos antes de permitir o avanço. Caso algum dado esteja vazio ou inválido, uma mensagem de erro é exibida ao usuário.

### Tela 2 — Opções da Viagem

A segunda tela permite escolher as opções adicionais da viagem.

Tipo de hospedagem:

- econômica;
- conforto;
- luxo.

Serviços adicionais:

- transporte;
- alimentação;
- passeios.

A hospedagem é selecionada com `RadioButton`, pois apenas uma opção pode ser escolhida. Os serviços são selecionados com `Checkbox`, pois o usuário pode marcar mais de uma opção.

### Tela 3 — Resumo da Viagem

A terceira tela exibe o resumo completo da viagem, incluindo:

- destino;
- número de dias;
- orçamento diário;
- tipo de hospedagem;
- serviços selecionados;
- valor total calculado.

Também há um botão para reiniciar o planejamento e voltar para a tela inicial.

## Regras de cálculo

O cálculo do valor total é feito a partir do custo base, da hospedagem e dos serviços adicionais.

```text
custoBase = dias * orçamentoDiário
```

Multiplicadores de hospedagem:

```text
Econômica = 1.0
Conforto  = 1.5
Luxo      = 2.2
```

Custos dos serviços adicionais:

```text
Transporte  = + R$ 300,00
Alimentação = + R$ 50,00 por dia
Passeios    = + R$ 120,00 por dia
```

Fórmula geral:

```text
total = (dias * orçamentoDiário * multiplicadorHospedagem) + extras
```

## Navegação

O aplicativo possui três `Activities`:

```text
MainActivity
TripOptionsActivity
TripSummaryActivity
```

O fluxo principal é:

```text
MainActivity -> TripOptionsActivity -> TripSummaryActivity
```

A navegação é feita com `Intent` explícita. Os dados são enviados entre as telas usando `putExtra` e recuperados com os métodos adequados, como `getStringExtra`, `getIntExtra`, `getDoubleExtra` e `getBooleanExtra`.

## Preservação de estado

As telas utilizam `rememberSaveable` para preservar os dados durante mudanças de configuração, como rotação da tela.

Dessa forma, os campos preenchidos e as opções selecionadas continuam salvos quando o dispositivo é rotacionado.

## Estrutura principal do projeto

```text
app/src/main/java/br/edu/ifsp/scl/bes/prdm/sc304453x/fasttripplanner/
│
├── MainActivity.kt
├── TripOptionsActivity.kt
├── TripSummaryActivity.kt
├── TripIntentKeys.kt
├── AccommodationType.kt
├── TripCalculator.kt
│
└── ui/composable/
    ├── TripDataScreen.kt
    ├── TripOptionsScreen.kt
    └── TripResumeScreen.kt
```

## Como executar

1. Clone o repositório.

```bash
git clone <url-do-repositorio>
```

2. Abra o projeto no Android Studio.

3. Aguarde a sincronização do Gradle.

4. Execute o aplicativo em um emulador ou dispositivo físico com Android 8.0 ou superior.

## Vídeo demonstrativo

O vídeo demonstrativo do funcionamento do aplicativo deve ser incluído na entrega, conforme solicitado no enunciado.

```text
demo/FastTripPlanner.mp4
https://youtube.com/shorts/bstGAMIsq7w
```

O vídeo mostra:

- preenchimento dos dados da viagem;
- validação dos campos;
- escolha da hospedagem;
- seleção dos serviços adicionais;
- exibição do resumo;
- cálculo do valor total;
- reinício do planejamento;
- preservação dos dados ao rotacionar a tela.

## Autor

```text
Nome: Fernando Souza De Riggi
Prontuário: SC304453X
Curso: Engenharia de Software
Instituição: IFSP São Carlos
```
