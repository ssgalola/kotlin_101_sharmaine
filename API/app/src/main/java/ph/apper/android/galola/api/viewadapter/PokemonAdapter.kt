package ph.apper.android.galola.api.viewadapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_pokemonlist_row.view.*
import ph.apper.android.galola.api.R
import ph.apper.android.galola.api.model.Pokemon

class PokemonAdapter(private val context: Context,
                     private var pokemonlist: ArrayList<Pokemon>)
    : RecyclerView.Adapter<PokemonAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_pokemonlist_row, parent, false))

    override fun getItemCount() = pokemonlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(pokemonlist[position])
        holder.btn_view_data.setOnClickListener{
            Log.d("Pokemon", "${pokemonlist[position].name}")

            Intent().also {
                Log.d("Pokemon", "${pokemonlist[position].url}")
                it.setAction("ph.apper.android.api.broadcast.GETDATA")
                it.putExtra("data", pokemonlist[position].url)
                context.sendBroadcast(it)
            }
        }
    }

    fun setList(pokemonlist: ArrayList<Pokemon>){
        this.pokemonlist.clear()
        this.pokemonlist.addAll(pokemonlist)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val pokemon_name = view.pokemon_name
        public var btn_view_data = view.btn_view_data

        fun bindItems(pokemon: Pokemon){
            pokemon_name.text = pokemon.name.capitalize()
        }
    }
}