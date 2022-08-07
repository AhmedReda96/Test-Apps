package ar.test_apps.presentationLayer.vm.pokemonApp

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.test_apps.dataLayer.repository.pokemonApp.PokemonRepository
import ar.test_apps.domainLayer.baseResponse.PokemonNetworkState
import ar.test_apps.domainLayer.entity.pokemon.PokemonRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repo: PokemonRepository) : ViewModel() {
    private var pokemonMLD = MutableLiveData<PokemonNetworkState>()
    val pokemonLD: LiveData<PokemonNetworkState> get() = pokemonMLD

    fun getPokemon(request: PokemonRequest) {
        pokemonMLD.postValue(PokemonNetworkState.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                pokemonMLD.postValue(
                    PokemonNetworkState.Success(
                        request,
                        repo.getPokemon()
                    )
                )
            } catch (e: Exception) {
                pokemonMLD.postValue(PokemonNetworkState.Error(request, e))
                e.printStackTrace()
            }


        }
    }

}