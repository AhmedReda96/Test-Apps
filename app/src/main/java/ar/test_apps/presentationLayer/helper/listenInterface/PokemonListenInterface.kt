package ar.test_apps.presentationLayer.helper.listenInterface

import ar.test_apps.domainLayer.entity.pokemon.PokemonResponseEntity

interface PokemonListenInterface {
    fun clickOnPokemonItem(model:PokemonResponseEntity?)
}