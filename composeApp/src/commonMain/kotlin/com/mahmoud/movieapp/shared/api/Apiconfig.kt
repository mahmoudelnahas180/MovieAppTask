package com.mahmoud.movieapp.shared.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object Apiconfig {
    const val Base_URl = "https://fooapi.com/api"

    val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
}