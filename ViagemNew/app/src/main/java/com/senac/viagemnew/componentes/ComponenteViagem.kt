package com.senac.viagemnew.componentes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.senac.viagemnew.model.TipoViagem

@Composable
fun EntradaDestino(destino: String, MudouDestino: (String) -> Unit) {
    OutlinedTextField(
        value = destino,
        onValueChange = MudouDestino,
        label = { Text("Destino") }
    )
}
@Composable
fun EntradaTipoViagem(tripType: TipoViagem, MudouTipoViagem: (TipoViagem) -> Unit) {
    Row {
        TipoViagem.values().forEach { tipo ->
            Row {
                RadioButton(
                    selected = tripType == tipo,
                    onClick = { MudouTipoViagem(tipo) }
                )
                Text(tipo.name)
            }
        }
    }
}
@Composable
fun EntradaValor(valor: String, MudouValor: (String) -> Unit) {
    OutlinedTextField(
        value = valor,
        onValueChange = MudouValor,
        label = { Text("Orçamento") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}