package ph.apper.android.galola.showtracker

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import ph.apper.android.galola.showtracker.model.Show
import ph.apper.android.galola.showtracker.model.ShowGenres
import ph.apper.android.galola.showtracker.model.ShowType

class MainActivity : AppCompatActivity() {
    companion object {
        var showList: ArrayList<Show> = ArrayList()

        fun addShow(show: Show){
            showList.add(show)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        populateShow()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun populateShow(){
        showList = ArrayList()

        var temp = Show("Jujutsu Kaisen", ShowType.ANIME, ShowGenres.ACTION)
        showList.add(temp)

        temp = Show("Beauty Inside", ShowType.KDRAMA, ShowGenres.DRAMA)
        showList.add(temp)

        temp = Show("The Big Bang Theory", ShowType.SERIES, ShowGenres.COMEDY)
        showList.add(temp)

        temp = Show("18 Again", ShowType.KDRAMA, ShowGenres.ROMANCE)
        showList.add(temp)

        temp = Show("Civil War", ShowType.MOVIE, ShowGenres.ACTION)
        showList.add(temp)

        temp = Show("WandaVision", ShowType.SERIES, ShowGenres.ROMANCE)
        showList.add(temp)

        temp = Show("Kakegurui", ShowType.ANIME, ShowGenres.ANIMATION)
        showList.add(temp)
    }
}