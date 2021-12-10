package de.gally.moviespringboot.adapter

import de.gally.moviespringboot.domain.ClientException
import de.gally.moviespringboot.domain.InternalException
import de.gally.moviespringboot.domain.Movie
import de.gally.moviespringboot.domain.MovieServices
import de.gally.moviespringboot.getLogger
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.awaitBody

@Service
class OmdbHttpWebClient(
    private val properties: OmdbProperties,
) : MovieServices {

    private val webClient: WebClient by lazy {
        WebClient.builder()
            .baseUrl(properties.baseUrl)
            .build()
    }

    override suspend fun fetchMovieByTitle(title: String): Movie {
        val request = webClient
            .get()
            .uri {
                it
                    .queryParam("apikey", properties.apiKey)
                    .queryParam("t", title)
                    .build()
            }
            .retrieve()

        return handleRequestGracefully { request.awaitBody() }
    }

    private suspend fun <T> handleRequestGracefully(request: suspend () -> T): T {
        return runCatching { request() }
            .onFailure {
                when (it) {
                    is WebClientResponseException -> {
                        val body = it.responseBodyAsString
                        val requestUri = it.request?.uri
                        val requestMethod = it.request?.methodValue

                        throw ClientException(
                            "Requested Uri [$requestMethod $requestUri] with body [$body]",
                            it.statusCode
                        )
                    }
                    else -> throw InternalException("Unhandled Exception was thrown in OmdbHttpWebClient: $it")
                }
            }.getOrThrow()
    }
}

@ConstructorBinding
@ConfigurationProperties(prefix = "omdb")
data class OmdbProperties(
    val baseUrl: String,
    val apiKey: String
) {
    init {
        getLogger<OmdbProperties>().info("Omdb Api-Key: [$apiKey]")
    }
}
