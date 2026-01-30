package com.jhonatan.prueba1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.jhonatan.prueba1.core.di.AppContainer
import com.jhonatan.prueba1.features.library.di.BooksModule
import com.jhonatan.prueba1.features.library.presentation.screens.BooksScreen


class MainActivity : ComponentActivity() {
    lateinit var appContainer: AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = AppContainer(this)
        val booksModule = BooksModule(appContainer)
        enableEdgeToEdge()
        setContent {
            MaterialTheme() {
                Surface(color = MaterialTheme.colorScheme.background) {
                    BooksScreen(
                        factory = booksModule.provideBooksViewModelFactory()
                    )
                }
            }
        }
    }
}
