package com.senac.viagemnew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senac.viagemnew.componentes.Calendario
import com.senac.viagemnew.componentes.EntradaDestino
import com.senac.viagemnew.componentes.EntradaTipoViagem
import com.senac.viagemnew.componentes.EntradaValor
import com.senac.viagemnew.model.TipoViagem
import com.senac.viagemnew.model.viagem
import com.senac.viagemnew.ui.theme.ViagemNewTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViagemNewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NovaViagem()
                }
            }
        }
    }
}


@Composable
fun NovaViagem() {
    val snackbarHostState = remember { SnackbarHostState() }
    val trip = remember {
        mutableStateOf(
            viagem(
                destino = "",
                tipo = TipoViagem.LAZER,
                DataInicial = Date(),
                DataFinal = Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000),
                valor = 0.0
            )
        )
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Blue.copy(alpha = 0.1f))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {}) {
                            Text("<--")
                        }
                        Text(
                            text = "Nova Viagem",
                            modifier = Modifier.weight(0.1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    EntradaDestino(
                        destino = trip.value.destino,
                        MudouDestino = { destino -> trip.value = trip.value.copy(destino = destino) }
                    )
                    EntradaTipoViagem(
                        tripType = trip.value.tipo,
                        MudouTipoViagem = { tipo -> trip.value = trip.value.copy(tipo = tipo) }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Calendario(
                        label = "Data Inícial",
                        data = trip.value.DataInicial,
                        DataSelecionada = { newDate ->
                            trip.value = trip.value.copy(DataInicial = newDate)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Calendario(
                        label = "Data Final",
                        data = trip.value.DataFinal,
                        DataSelecionada = { newDate ->
                            trip.value = trip.value.copy(DataFinal = newDate)
                        }
                    )
                    EntradaValor(
                        valor = formatValue(trip.value.valor),
                        MudouValor = { newValue ->
                            trip.value = trip.value.copy(valor = newValue.toDoubleOrNull() ?: 0.0)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.Main).launch {
                                snackbarHostState.showSnackbar("Viagem Registrada!")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Salvar", color = Color.Black)
                    }
                }
            }
        }
    )
}


@Composable
fun formatValue(value: Double): String {
    val intValue = value.toInt()
    return if (value == intValue.toDouble()) {
        intValue.toString()
    } else {
        value.toString()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViagemNewTheme {
        NovaViagem()
    }
}