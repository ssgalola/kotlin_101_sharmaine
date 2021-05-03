package ph.apper.android.galola.ecommerce2.models

data class User(val email:String, var password:String) {

    constructor() : this("", "")
}

enum class SpinnerRegistration {
    Male,
    Female,
    Others;

    companion object {
        fun getCategory(category: String) =
            when(category) {
                "Male" -> Male
                "Female" -> Female
                else -> Others
            }
    }
}