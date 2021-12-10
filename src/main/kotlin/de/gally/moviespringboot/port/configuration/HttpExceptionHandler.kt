package de.gally.moviespringboot.port.configuration

import de.gally.moviespringboot.domain.ClientException
import de.gally.moviespringboot.domain.InternalException
import org.slf4j.MDC
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class HttpExceptionHandler {

    @ExceptionHandler(ClientException::class)
    fun handleThrowables(clientException: ClientException): ResponseEntity<ErrorMessage> {
        return ResponseEntity.status(clientException.status).body(ErrorMessage(clientException.message))
    }

    @ExceptionHandler(InternalException::class)
    fun handleThrowables(clientException: InternalException): ResponseEntity<ErrorMessage> {
        return ResponseEntity.status(500).body(ErrorMessage(clientException.message))
    }

    data class ErrorMessage(
        val errorMessage: String?,
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val trackingId: String? = MDC.get(AppHeader.TRACKING_ID)
    )
}
