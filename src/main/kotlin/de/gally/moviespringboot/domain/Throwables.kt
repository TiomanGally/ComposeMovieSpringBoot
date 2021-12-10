package de.gally.moviespringboot.domain

import org.springframework.http.HttpStatus

class InternalException(cause: String) : Throwable(cause)
class ClientException(cause: String, val status: HttpStatus) : Throwable(cause)
