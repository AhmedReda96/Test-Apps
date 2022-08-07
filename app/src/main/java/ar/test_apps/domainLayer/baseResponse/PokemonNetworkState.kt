package ar.test_apps.domainLayer.baseResponse

sealed class PokemonNetworkState {
    var loader: Loading? = null
    var request: Any? = null

    open class Loading : PokemonNetworkState() {}

    class Success<T>(val item: T) : PokemonNetworkState() where T : Any {
        constructor(request: T?, response: T) : this(response) {
            this.request = request
        }
    }

    class Error(val error: Throwable) : PokemonNetworkState() {
        constructor(request: Any?, error: Throwable) : this(error) {
            this.request = request
        }
    }

}




