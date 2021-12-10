package de.gally.moviespringboot

import de.gally.moviespringboot.adapter.OmdbProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(OmdbProperties::class)
class MovieSpringBootApplication

fun main(args: Array<String>) {
    runApplication<MovieSpringBootApplication>(*args)
}

internal inline fun <reified T> getLogger(): Logger = LoggerFactory.getLogger(T::class.java)
