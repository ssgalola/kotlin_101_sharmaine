package ph.apper.android.galola.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ph.apper.android.galola.api.viewadapter.PokemonInfoAdapter

class MainActivity : AppCompatActivity() {
    val pokemonListFragment = PokemonListFragment()
    val pokemonInfoFragment = PokemonInfoFragment()
    val pokemonImageFragment = PokemonImageFragment()

    lateinit var pokemonInfoAdapter : PokemonInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_holder, pokemonListFragment)
            .commit()

        pokemonInfoAdapter = PokemonInfoAdapter(supportFragmentManager)
        pokemonInfoAdapter.add(pokemonInfoFragment, "Pokemon Information")
        pokemonInfoAdapter.add(pokemonImageFragment, "Pokemon Image")
        pokemon_info_viewpager.adapter = pokemonInfoAdapter
    }
}