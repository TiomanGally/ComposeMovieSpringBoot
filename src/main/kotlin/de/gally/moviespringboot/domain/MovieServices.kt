package de.gally.moviespringboot.domain

interface MovieServices {

    suspend fun fetchMovieByTitle(title: String): Movie
}
