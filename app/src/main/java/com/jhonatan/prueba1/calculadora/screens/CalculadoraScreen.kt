package com.jhonatan.prueba1.calculadora.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jhonatan.prueba1.calculadora.viewmodel.CalculadoraViewModel

@Composable
fun CalculadoraScreen(
    viewModel: CalculadoraViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = viewModel.operacionTexto,
                fontSize = 60.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = viewModel.resultadoTexto,
                fontSize = 50.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }

        val botones = arrayListOf(
            arrayListOf("C", "DEL", "/", "x"),
            arrayListOf("7", "8", "9", "-"),
            arrayListOf("4", "5", "6", "+"),
            arrayListOf("1", "2", "3", "="),
            arrayListOf("0", ".")
        )

        botones.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { char ->
                    Button(
                        onClick = {
                            when (char) {
                                "C" -> viewModel.onLimpiarClick()
                                "DEL" -> viewModel.onBorrarClick()
                                "=" -> viewModel.calcular()
                                "+", "-", "x", "/" -> viewModel.onOperadorClick(char)
                                else -> viewModel.onNumeroClick(char)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(vertical = 4.dp),
                        colors = when {
                            char == "=" -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
                            listOf("C", "DEL").contains(char) -> ButtonDefaults.buttonColors(containerColor = Color.Red)
                            listOf("+", "-", "x", "/").contains(char) -> ButtonDefaults.buttonColors(containerColor = Color.Gray)
                            else -> ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant, contentColor = MaterialTheme.colorScheme.onSurfaceVariant)
                        },
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(text = char, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
                repeat(4 - row.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}


@Preview (showBackground = true)
@Composable
fun CalculadoraScreenPreview() {
    CalculadoraScreen()
}
