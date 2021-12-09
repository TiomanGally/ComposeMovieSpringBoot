package de.gally.moviespringboot.adapter

import de.gally.moviespringboot.domain.Movie
import de.gally.moviespringboot.domain.MovieServices
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class OmdbHttpWebClient(
    private val properties: OmdbProperties
) : MovieServices {

    private val webClient: WebClient by lazy {
        WebClient.builder()
            .baseUrl(properties.baseUrl)
            .build()
    }

    override suspend fun fetchMovieByTitle(title: String): Movie {
        return webClient
            .get()
            .uri {
                it
                    .queryParam("apikey", properties.apiKey)
                    .queryParam("t", title)
                    .build()
            }
            .retrieve()
            .awaitBody()
    }
}

@ConstructorBinding
@ConfigurationProperties(prefix = "omdb")
data class OmdbProperties(
    val baseUrl: String,
    val apiKey: String
) {
    init {
        LoggerFactory.getLogger(this::class.java).info("The properties have following values: $baseUrl and $apiKey")
    }
}
