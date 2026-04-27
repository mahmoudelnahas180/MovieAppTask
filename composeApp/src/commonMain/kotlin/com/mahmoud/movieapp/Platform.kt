package com.mahmoud.movieapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
