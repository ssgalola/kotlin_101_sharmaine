package ph.apper.android.galola.api

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_pokemonlist.*
import kotlinx.android.synthetic.main.fragment_pokemonlist.view.*
import ph.apper.android.galola.api.api.PokemonAPIClient
import ph.apper.android.galola.api.model.Pokemon
import ph.apper.android.galola.api.model.PokemonListResponse
import ph.apper.android.galola.api.viewadapter.PokemonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListFragment : Fragment() {

    lateinit var  pokemonAdapter: PokemonAdapter
    var pokemonList = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_pokemonlist, container, false)

        pokemonAdapter = PokemonAdapter(activity!!.applicationContext, pokemonList)
        view.pokemon_list.adapter = pokemonAdapter
        view.pokemon_list.layoutManager = LinearLayoutManager(activity!!.applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        getData()
        return view
    }

    fun getData(){
        val call: Call<PokemonListResponse> =
            PokemonAPIClient.getPokemonData.getList(0, 100)

        call.enqueue(object : Callback<PokemonListResponse>{
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                Log.d("API CALL", "Failed API CALL")
            }

            override fun onResponse(call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>) {
                var response: PokemonListResponse = response!!.body()!!

                pokemonAdapter.setList(response.pokemonList)
//                var pokelist = response.pokemonList
//                for (pokemon in pokelist){
//                    Log.d("API CALL", pokemon.name)
//                }
            }

        })
    }
}