package ph.apper.android.galola.api

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_pokemoninfo.*
import kotlinx.android.synthetic.main.item_pokemonlist_row.*
import kotlinx.android.synthetic.main.item_pokemonlist_row.pokemon_name
import ph.apper.android.galola.api.api.PokemonAPIClient
import ph.apper.android.galola.api.extensions.getPokemonID
import ph.apper.android.galola.api.model.PokemonInfoResponse
import ph.apper.android.galola.api.viewadapter.PokemonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonInfoFragment : Fragment() {

    private val receiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            var message: String? = intent!!.getStringExtra("data")

            Log.d("Message", message!!)

            message?.let{
                getData(message.getPokemonID())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_pokemoninfo, container, false)

        return view
    }

    override fun onDestroyView() {
        activity!!.unregisterReceiver(receiver)
        super.onDestroyView()
    }

    private fun setupReceiver(){
        val intentFilter = IntentFilter()
        intentFilter.addAction("ph.apper.android.api.broadcast.GETDATA")
        activity!!.registerReceiver(receiver, intentFilter)
    }

    private fun getData(id: Int){
        Log.d("ID", id.toString())
        val call: Call<PokemonInfoResponse> =
            PokemonAPIClient.getPokemonData.getPokemonInfo(id)

        call.enqueue(object : Callback<PokemonInfoResponse> {
            override fun onFailure(call: Call<PokemonInfoResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<PokemonInfoResponse>,
                response: Response<PokemonInfoResponse>) {

                    var response: PokemonInfoResponse = response!!.body()!!
                    Log.d("API INFO CALL", response.name)

                    Intent().also {
                        Log.d("Pokemon", "${response.sprites.front_default}")
                        it.setAction("ph.apper.android.api.broadcast.LOADIMAGEACTION")
                        it.putExtra("data", response.sprites.front_default)
                        context!!.sendBroadcast(it)
                    }

                    val p_name: TextView = pokemon_name.findViewById(R.id.pokemon_name)
                    p_name.text = response.name.capitalize()

                    val p_id: TextView = pokemon_id.findViewById(R.id.pokemon_id)
                    p_id.text = response.id.toString()

                    val p_height: TextView = pokemon_height.findViewById(R.id.pokemon_height)
                    p_height.text = response.height.toString()

                    val list_abilities: ArrayList<String> = ArrayList<String>()
                        for (a in response.abilities){
                            list_abilities.add(a.ability.name.capitalize())
                        }
                    val p_abilities: TextView = pokemon_abilities.findViewById(R.id.pokemon_abilities)
                    p_abilities.text = list_abilities
                                            .toString()
                                            .substring(1, list_abilities.toString().length-1)

            }

            })
        }
    }
