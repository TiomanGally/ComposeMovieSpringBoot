package de.gally.moviespringboot.port.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.*

@Configuration
class HttpInterceptor : WebFilter {

    private companion object {
        const val TRACKING_ID: String = "X-TrackingId"
    }

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        with(exchange.request) {
            val queryParams = queryParams().ifEmpty { "" }
            val appHeaders = appHeaders().ifEmpty { "" }

            log.info("${trackingId()} Request => [$methodValue] $path$queryParams - $appHeaders")
        }

        with(exchange.response) {
            val trackingId = MDC.get(TRACKING_ID)
            
            log.info("$trackingId Response => [$statusCode]")
        }
        
        return chain.filter(exchange)
    }

    private fun ServerHttpRequest.trackingId(): String {
        val trackingId = this.headers[TRACKING_ID]?.first() ?: UUID.randomUUID().toString()

        MDC.put(TRACKING_ID, trackingId)

        return trackingId
    }

    private fun ServerHttpRequest.queryParams(): Map<String, String> {
        return this.queryParams.mapValues { it.value.first() }
    }

    private fun ServerHttpRequest.appHeaders(): Map<String, String> {
        return this.headers.filterKeys { it.startsWith("X-") && it != TRACKING_ID }.mapValues { it.value.first() }
    }
}
