package com.mahmoud.movieapp.shared.repository

import com.mahmoud.movieapp.shared.api.ApiService
import com.mahmoud.movieapp.shared.model.Movie

class MovieRepository() {
    private val  apiService: ApiService= ApiService()
    suspend fun getMovies(): List<Movie>{
        return   apiService.getMovies()

    }

}