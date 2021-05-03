package ph.apper.android.galola.api.extensions

fun String.getPokemonID() : Int {
    var value = this.toString()
    var id = value.substring(34, value.length-1)
    return id.toInt()
}