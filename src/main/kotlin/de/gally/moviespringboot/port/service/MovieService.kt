package de.gally.moviespringboot.port.service

import de.gally.moviespringboot.domain.Movie
import de.gally.moviespringboot.domain.MovieServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieServices: MovieServices
) {

    /** This will fetch all received movies by its [titles] in parallel */
    suspend fun fetchMovieByTitles(titles: List<String>): List<Movie> {
        return titles.map {
            withContext(Dispatchers.IO) {
                async { movieServices.fetchMovieByTitle(it) }
            }
        }.awaitAll()
    }
}
