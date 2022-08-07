package ar.test_apps.dataLayer.repository.pokemonApp

import android.util.Log
import ar.test_apps.dataLayer.remote.ApiService
import ar.test_apps.domainLayer.baseResponse.PokemonApiResponse
import ar.test_apps.domainLayer.entity.pokemon.PokemonResponseEntity
import javax.inject.Inject
import kotlin.math.log

class PokemonRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPokemon(): PokemonApiResponse<List<PokemonResponseEntity>> {
        return apiService.getPokemon()
    }
}