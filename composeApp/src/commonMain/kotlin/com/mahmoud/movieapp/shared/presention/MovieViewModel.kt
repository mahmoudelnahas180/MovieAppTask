package com.mahmoud.movieapp.shared.presention

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud.movieapp.shared.model.Movie
import com.mahmoud.movieapp.shared.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {
    private val repository = MovieRepository()

    private val Movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = Movies.asStateFlow()
    
    private val IsLoading = MutableStateFlow(false)
    val isLoading = IsLoading.asStateFlow()
    
    private val Error = MutableStateFlow<String?>(null)
    val error = Error.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            IsLoading.value = true
            Error.value = null
            
            try {
                val movieList = repository.getMovies()

                Movies.value = movieList

            } catch (e: Exception) {
                Error.value = "Failed to load movies: ${e.message}"

            } finally {
                IsLoading.value = false
            }
        }
    }
}