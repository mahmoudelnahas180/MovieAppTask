package com.mahmoud.movieapp.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val data: List<Movie>
)
