package ar.test_apps.dataLayer.remote

import ar.test_apps.domainLayer.baseResponse.PokemonApiResponse
import ar.test_apps.domainLayer.entity.pokemon.PokemonResponseEntity
import retrofit2.http.GET

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemon(): PokemonApiResponse<List<PokemonResponseEntity>>
}