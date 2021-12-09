package de.gally.moviespringboot.port

import de.gally.moviespringboot.domain.Movie
import de.gally.moviespringboot.port.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/movies/")
class MovieRestController(
    private val movieService: MovieService
) {

    @GetMapping("/{titles}")
    suspend fun getMovieByTitle(
        @PathVariable titles: List<String>
    ): ResponseEntity<List<Movie>> {
        val movies = movieService.fetchMovieByTitles(titles)

        return ResponseEntity.ok(movies)
    }
}
