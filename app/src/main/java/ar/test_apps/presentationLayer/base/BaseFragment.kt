package ar.test_apps.presentationLayer.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ar.test_apps.R
import ar.test_apps.domainLayer.baseResponse.ApiResponse
import ar.test_apps.domainLayer.baseResponse.PokemonNetworkState
import ar.test_apps.utils.AppPreferences
import ar.test_apps.utils.showLog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    @Inject
    lateinit var appPreferences: AppPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    fun handlePokemonUI(viewState: PokemonNetworkState) {
        when (viewState) {
            is PokemonNetworkState.Loading -> {
                showProgressDialog(getString(R.string.loading))
            requireContext().showLog("handlePokemonUI","Loading")
            }

            is PokemonNetworkState.Error -> {
                try {
                    hideLoader()
                    handleError(viewState.request)
                    viewState.error.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                requireContext().showLog("handlePokemonUI","ERROR")

            }

            is PokemonNetworkState.Success<*> -> {
                hideLoader()
                when (viewState.item) {
                    is ApiResponse<*> -> {
                        viewState.item.request = viewState.request
                        handleResponse(viewState)
                    }else ->                 requireContext().showLog("handlePokemonUI","els")

                }

            }

            else -> throw IllegalArgumentException("Unknown view state ${viewState.javaClass.simpleName}")
        }
    }

    private fun handleResponse(response: PokemonNetworkState.Success<*>) {
        handleResponse(response.item as ApiResponse<*>)
    }

    @Suppress("UNCHECKED_CAST")
    protected open fun handleResponse(response: ApiResponse<*>) {
    }

    private fun handleError(request: Any?) {
        Toast.makeText(
            requireContext(),
            getString(R.string.message_server_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun hideLoader() {

    }

    private fun showProgressDialog(message: Any) {
        //todo progress init
    }


    fun setUrlType(url: String) = appPreferences.setUrlType(url)

}