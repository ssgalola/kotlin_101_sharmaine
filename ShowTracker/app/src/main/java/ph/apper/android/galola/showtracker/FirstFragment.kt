package ph.apper.android.galola.showtracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*
import ph.apper.android.galola.showtracker.model.Show
import ph.apper.android.galola.showtracker.model.ShowGenres
import ph.apper.android.galola.showtracker.model.ShowType

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

    }

    private fun init(view: View) {
        var typeArray = arrayOf(
            ShowType.UNDEFINED.toString(),
            ShowType.ANIME.toString(),
            ShowType.MOVIE.toString(),
            ShowType.KDRAMA.toString(),
            ShowType.OVA.toString(),
            ShowType.SERIES.toString()
        )

        val arrayTypeAdapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, typeArray)
        spinner_type.adapter = arrayTypeAdapter

        spinner_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("Spinner Data", "msg: Selected Type: ${typeArray[position]}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

            var genreArray = arrayOf(
                ShowGenres.UNDEFINED.toString(),
                ShowGenres.ANIMATION.toString(),
                ShowGenres.ROMANCE.toString(),
                ShowGenres.COMEDY.toString(),
                ShowGenres.ACTION.toString(),
                ShowGenres.DRAMA.toString(),
                ShowGenres.HORROR.toString(),
                ShowGenres.MUSICAL.toString(),
                ShowGenres.THRILLER.toString()
            )

            val arrayGenreAdapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, genreArray)
            spinner_genre.adapter = arrayGenreAdapter

            spinner_genre.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("Spinner Data", "msg: Selected Genre: ${genreArray[position]}")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //do nothing
                }
            }
            view.findViewById<Button>(R.id.button_first).setOnClickListener{
                //new show add here
                var title = et_title.text.toString()
                var type: String = spinner_type.selectedItem as String
                var genre: String = spinner_genre.selectedItem as String

                MainActivity.addShow(Show(
                    title,
                    ShowType.getType(type),
                    ShowGenres.getGenre(genre)
                ))
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }

