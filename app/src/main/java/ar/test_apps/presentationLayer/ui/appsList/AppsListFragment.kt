package ar.test_apps.presentationLayer.ui.appsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ar.test_apps.R
import ar.test_apps.databinding.FragmentAppsListBinding
import ar.test_apps.presentationLayer.base.BaseFragment
import ar.test_apps.utils.ConstantStrings
import ar.test_apps.utils.makeFragmentNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppsListFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentAppsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_apps_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.pokemonAppBtn.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.pokemonAppBtn -> {
                makeFragmentNavigate(R.id.pokemonListFragment)
                setUrlType(ConstantStrings.POKEMON_URL)
            }
        }

    }

}