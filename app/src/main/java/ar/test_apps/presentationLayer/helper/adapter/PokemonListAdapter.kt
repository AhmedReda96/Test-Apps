package ar.test_apps.presentationLayer.helper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ar.test_apps.R
import ar.test_apps.databinding.PokemonItemModelBinding
import ar.test_apps.domainLayer.entity.pokemon.PokemonResponseEntity
import ar.test_apps.presentationLayer.helper.listenInterface.PokemonListenInterface

class PokemonListAdapter(private var listener: PokemonListenInterface) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {
    private lateinit var binding: PokemonItemModelBinding
    private var pokemonList: ArrayList<PokemonResponseEntity>? = null

    class PokemonListViewHolder(binding: View) : RecyclerView.ViewHolder(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.pokemon_item_model,
            parent,
            false
        )
        return PokemonListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val model: PokemonResponseEntity? = pokemonList?.get(position)
        binding.pokemonTitle.text = model?.name ?: ""

        binding.root.setOnClickListener {
            listener.clickOnPokemonItem(model)
        }
    }


    override fun getItemCount(): Int {
        return pokemonList?.size ?: 0
    }

     fun setItems(items: ArrayList<PokemonResponseEntity>?) {
        this.pokemonList = items
        notifyDataSetChanged()
    }


}