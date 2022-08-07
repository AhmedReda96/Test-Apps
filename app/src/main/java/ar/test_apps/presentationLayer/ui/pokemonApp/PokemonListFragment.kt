package ar.test_apps.presentationLayer.ui.pokemonApp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import ar.test_apps.R
import ar.test_apps.databinding.FragmentPokemonListBinding
import ar.test_apps.domainLayer.baseResponse.ApiResponse
import ar.test_apps.domainLayer.baseResponse.PokemonApiResponse
import ar.test_apps.domainLayer.baseResponse.PokemonNetworkState
import ar.test_apps.domainLayer.entity.pokemon.PokemonRequest
import ar.test_apps.domainLayer.entity.pokemon.PokemonResponseEntity
import ar.test_apps.presentationLayer.base.BaseFragment
import ar.test_apps.presentationLayer.helper.adapter.PokemonListAdapter
import ar.test_apps.presentationLayer.helper.listenInterface.PokemonListenInterface
import ar.test_apps.presentationLayer.vm.pokemonApp.PokemonViewModel
import ar.test_apps.utils.showLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : BaseFragment(), PokemonListenInterface {
    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var binding: FragmentPokemonListBinding
    private var pokemonListAdapter: PokemonListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.pokemonRV.apply {
            layoutManager =
                GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            pokemonListAdapter = PokemonListAdapter(this@PokemonListFragment)
            adapter = pokemonListAdapter
        }

        viewModel.pokemonLD.observe(viewLifecycleOwner) { handlePokemonUI(it) }


        getPokemonList()
    }

    private fun getPokemonList() {
        viewModel.getPokemon(PokemonRequest())
    }

    override fun handleResponse(response: ApiResponse<*>) {
        when (response.request) {
            is PokemonRequest -> {
                val result = response.data as List<PokemonResponseEntity>
                pokemonListAdapter?.setItems(result as ArrayList<PokemonResponseEntity>)
                requireContext().showLog("handlePokemonUI", result.size.toString())
            }
        }
    }

    override fun clickOnPokemonItem(model: PokemonResponseEntity?) {

    }
}