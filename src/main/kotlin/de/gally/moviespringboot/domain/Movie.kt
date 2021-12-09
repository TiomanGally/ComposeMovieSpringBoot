package de.gally.moviespringboot.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Movie(
    @JsonProperty("Title") val title: String = "",
    @JsonProperty("Year") val year: String = "",
    @JsonProperty("Rated") val rated: String = "",
    @JsonProperty("Released") val release: String = "",
    @JsonProperty("Runtime") val runtime: String = "",
    @JsonProperty("Genre") val genre: String = "",
    @JsonProperty("Director") val director: String = "",
    @JsonProperty("Writer") val writer: String = "",
    @JsonProperty("Actors") val actor: String = "",
    @JsonProperty("Plot") val description: String = "",
    @JsonProperty("Awards") val awards: String = "",
    @JsonProperty("Poster") val posterLink: String = "",
    @JsonProperty("Ratings") val ratings: List<Rating> = emptyList(),
    @JsonProperty("Metascore") val metaScoreRating: String = "",
    val imdbRating: String = "",
    @JsonProperty("Response") val response: String?,
    @JsonProperty("Error") val error: String?,
) {
    /** If a movie does exist it will return true */
    fun isAvailable(): Boolean = response == "TRUE"
}

data class Rating(
    @JsonProperty("Source") val source: String,
    @JsonProperty("Value") val year: String
)
