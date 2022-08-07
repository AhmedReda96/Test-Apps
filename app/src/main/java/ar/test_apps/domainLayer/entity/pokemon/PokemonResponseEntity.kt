package ar.test_apps.domainLayer.entity.pokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonResponseEntity(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null
)