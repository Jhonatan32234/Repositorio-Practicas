package com.jhonatan.prueba1.counter.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CounterScreen (viewModel: CounterViewModel = viewModel()) {
    var count = viewModel.count.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .background(color = Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text("${count.value}",
                fontSize = 50.sp,
                color = Color.White
            )
        }
        Row(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .background(color = Color.Gray),
            verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {viewModel.decrement()},
                modifier = Modifier
                    .weight(1f)) {
                Text("-")

            }
            Button(onClick = {viewModel.increment()},
                modifier = Modifier
                    .weight(1f)) {
                Text("+")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview () {
    CounterScreen()
}