package com.jhonatan.prueba1.ahorcado.presentation.screens

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
import com.jhonatan.prueba1.ahorcado.presentation.viewmodel.AhorcadoViewModel

@Composable
fun AhorcadoScreen(
    viewModel: AhorcadoViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = when (viewModel.estadoJuego) {
                    "GANADO" -> "¡GANASTE!"
                    "PERDIDO" -> "PERDISTE"
                    else -> "AHORCADO"
                },
                fontSize = 32.sp,
                color = when (viewModel.estadoJuego) {
                    "GANADO" -> Color.Green
                    "PERDIDO" -> Color.Red
                    else -> MaterialTheme.colorScheme.primary
                },
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = viewModel.palabraMostrada(),
                fontSize = 40.sp,
                letterSpacing = 8.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Intentos: ${viewModel.intentosRestantes}",
                    fontSize = 24.sp,
                    color = when {
                        viewModel.intentosRestantes > 3 -> Color.Green
                        viewModel.intentosRestantes > 1 -> Color.Red
                        else -> Color.Red
                    }
                )

                Text(
                    text = "Usadas: ${viewModel.letrasUsadas}",
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botones del teclado
        val filas = listOf(
            listOf("A", "B", "C", "D", "E", "F"),
            listOf("G", "H", "I", "J", "K", "L"),
            listOf("M", "N", "Ñ", "O", "P", "Q"),
            listOf("R", "S", "T", "U", "V", "W"),
            listOf("X", "Y", "Z")
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            filas.forEach { fila ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    fila.forEach { letra ->
                        Button(
                            onClick = { viewModel.adivinarLetra(letra[0]) },
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                            enabled = viewModel.estadoJuego == "JUGANDO" && !viewModel.letraYaUsada(letra),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (viewModel.letraYaUsada(letra)) Color.LightGray
                                else MaterialTheme.colorScheme.primary,
                                contentColor = if (viewModel.letraYaUsada(letra)) Color.DarkGray
                                else Color.White
                            ),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(
                                text = letra,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    repeat(6 - fila.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.reiniciarJuego() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "NUEVO JUEGO", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { /* Mostrar palabra */ },
                modifier = Modifier.weight(1f),
                enabled = viewModel.estadoJuego != "JUGANDO",
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(
                    text = if (viewModel.estadoJuego == "JUGANDO") "ADIVINAR"
                    else "Palabra: ${viewModel.revelarPalabra()}",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AhorcadoScreenPreview() {
    MaterialTheme {
        AhorcadoScreen()
    }
}