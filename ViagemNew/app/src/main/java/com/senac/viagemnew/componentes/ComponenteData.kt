package com.senac.viagemnew.componentes

import android.app.DatePickerDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun Calendario(label: String, data: Date, DataSelecionada: (Date) -> Unit) {
    val context = LocalContext.current
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    Button(onClick = {
        val calend = Calendar.getInstance()
        calend.time = data
        val selecaoData = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calend.set(year, month, dayOfMonth)
                DataSelecionada(calend.time)
            },
            calend.get(Calendar.YEAR),
            calend.get(Calendar.MONTH),
            calend.get(Calendar.DAY_OF_MONTH)
        )
        selecaoData.show()
    }) {
        Text(text = "$label: ${dateFormat.format(data)}")
    }
}