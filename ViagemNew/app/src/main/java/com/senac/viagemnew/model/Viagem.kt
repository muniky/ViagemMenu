package com.senac.viagemnew.model

import java.util.Date

data class viagem(
    var destino: String,
    var tipo: TipoViagem,
    var DataInicial: Date,
    var DataFinal: Date,
    var valor: Double
)
enum class TipoViagem {
    LAZER, NEGOCIOS
}