package com.jhonatan.prueba1.features.nummagico.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhonatan.prueba1.features.nummagico.presentation.viewmodels.NumMagicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessGameScreen(viewModel: NumMagicViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Número Mágico") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!uiState.isGameActive && !uiState.isCorrect && !uiState.isGameOver) {
                Text("Configuración", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(8.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = uiState.maxAttemptsInput,
                            onValueChange = { viewModel.onMaxAttemptsChange(it) },
                            label = { Text("Límite de intentos") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = uiState.minRangeInput,
                                onValueChange = { viewModel.onMinRangeChange(it) },
                                label = { Text("Mín") },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            OutlinedTextField(
                                value = uiState.maxRangeInput,
                                onValueChange = { viewModel.onMaxRangeChange(it) },
                                label = { Text("Máx") },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }

                        Button(
                            onClick = { viewModel.startGame() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Iniciar Juego")
                        }
                    }
                }
            }

            if (uiState.isGameActive || uiState.isCorrect || uiState.isGameOver) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = when {
                            uiState.isCorrect -> Color(0xFFC8E6C9)
                            uiState.isGameOver -> Color(0xFFFFCDD2)
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }
                    ),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                ) {
                    Text(
                        text = uiState.message,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (uiState.isGameActive) {
                Text(
                    text = "Intentos restantes: ${uiState.attemptsLeft}",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (uiState.attemptsLeft <= 2) Color.Red else Color.Gray
                )

                OutlinedTextField(
                    value = uiState.userNumInput,
                    onValueChange = { viewModel.onNumChange(it) },
                    label = { Text("Tu número") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    singleLine = true
                )

                Button(
                    onClick = { viewModel.checkNum() },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    enabled = uiState.userNumInput.isNotEmpty()
                ) {
                    Text("Adivinar")
                }
            }

            if (uiState.isGameOver || uiState.isCorrect) {
                Button(
                    onClick = { viewModel.startGame() },
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
                ) {
                    Text("Jugar de nuevo")
                }
            }
        }
    }
}