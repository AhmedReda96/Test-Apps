package ar.test_apps.domainLayer.baseResponse

import com.google.gson.annotations.SerializedName

open class PokemonApiResponse<T> constructor() {
    @SerializedName("count")
    var count: Int = 0

    @SerializedName("next")
    var next: String? = null

    @SerializedName("previous")
    var previous: String? = null

    @SerializedName("results")
    var data: T? = null

    @Transient
    var request: Any? = null

    constructor(count: Int, next: String, previous: String, data: T?) : this() {
        this.count = count
        this.next = next
        this.previous = previous
        this.data = data
    }

}