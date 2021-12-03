package de.gally.moviespringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieSpringBootApplication

fun main(args: Array<String>) {
    runApplication<MovieSpringBootApplication>(*args)
}
