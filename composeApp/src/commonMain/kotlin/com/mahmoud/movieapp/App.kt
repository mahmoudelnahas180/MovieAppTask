package com.mahmoud.movieapp


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import com.mahmoud.movieapp.shared.presention.MovieScreen


@Composable
@Preview
fun App() {


    MaterialTheme {
        MovieScreen()

    }
}


