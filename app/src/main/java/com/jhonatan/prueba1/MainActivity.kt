package com.jhonatan.prueba1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jhonatan.prueba1.features.library.presentation.screens.BooksScreen
import com.jhonatan.prueba1.features.nummagico.domain.usecases.NumMagicUseCase
import com.jhonatan.prueba1.features.nummagico.presentation.screens.GuessGameScreen
import com.jhonatan.prueba1.features.nummagico.presentation.viewmodels.NumMagicViewModel
import com.jhonatan.prueba1.features.nummagico.presentation.viewmodels.NumMagicViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

/*class MainActivity : ComponentActivity() {
    lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = AppContainer(this)

        val guessNumberUseCase = NumMagicUseCase()

        val gameViewModelFactory = NumMagicViewModelFactory(guessNumberUseCase)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val gameViewModel: NumMagicViewModel = viewModel(
                        factory = gameViewModelFactory
                    )

                    GuessGameScreen(viewModel = gameViewModel)
                }
            }
        }
    }
}*/

@AndroidEntryPoint
class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                BooksScreen()
            }
        }
    }
}