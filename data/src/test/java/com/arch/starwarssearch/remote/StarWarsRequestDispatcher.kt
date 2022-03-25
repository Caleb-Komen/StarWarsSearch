package com.arch.starwarssearch.remote

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class StarWarsRequestDispatcher: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path){
            CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readFromFile("character.json"))
            }
            FILMS_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readFromFile("character_film.json"))
            }
            PLANET_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readFromFile("character_planet.json"))
            }
            SPECIE_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readFromFile("character_specie.json"))
            }
            STARSHIP_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readFromFile("character_starship.json"))
            }
            VEHICLE_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readFromFile("character_vehicle.json"))
            }
            NON_EXISTING_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(readFromFile("not_found.json"))
            }
            "/people/?search=$EXISTING_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readFromFile("character_search_match.json"))
            }
            "/people/?search=$NON_EXISTING_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readFromFile("character_search_no_match.json"))
            }
            else -> throw IllegalArgumentException("Unknown request path '${request.path}'")
        }
    }

    private fun readFromFile(fileName: String): String{
        return javaClass.classLoader?.let {
            it.getResource(fileName)?.readText()
        }!!
    }

    companion object{
        const val CHARACTER_URL = "/api/people/1/"
        const val FILMS_URL = "/api/films/1/"
        const val PLANET_URL = "/api/planets/1/"
        const val SPECIE_URL = "/api/species/1/"
        const val STARSHIP_URL = "/api/starships/1/"
        const val VEHICLE_URL = "/api/vehicles/1/"
        const val NON_EXISTING_CHARACTER_URL = "/api/people/100000"
        const val EXISTING_SEARCH_PARAMS = "Luke"
        const val NON_EXISTING_SEARCH_PARAMS = "tyfrwvdjedtevs"
    }
}