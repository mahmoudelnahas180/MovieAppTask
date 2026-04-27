package com.mahmoud.movieapp.shared.api

import com.mahmoud.movieapp.shared.model.Movie
import com.mahmoud.movieapp.shared.model.MoviesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService {
    private val client = Apiconfig.client
    suspend fun getMovies(): List<Movie> {
        return try {
            val url = "${Apiconfig.Base_URl}/movies"

            val response = client.get(url)
            val moviesResponse: MoviesResponse = response.body()
            val movies: List<Movie> = moviesResponse.data

            movies
        } catch (e: Exception) {

            e.printStackTrace()
            emptyList()
        }
    }
}