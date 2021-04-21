package ph.apper.android.galola.ungguyan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_01.setOnClickListener(this)
        image_02.setOnClickListener(this)
        image_03.setOnClickListener(this)
        image_04.setOnClickListener(this)
        image_05.setOnClickListener(this)
        image_06.setOnClickListener(this)
        image_07.setOnClickListener(this)
        image_08.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var message = ""
        when (v!!.id){
            image_01.id -> {
                message = "Image 01"
                var nextActivityIntent: Intent = Intent(applicationContext, SecondActivity::class.java)
                finish()
                startActivity(nextActivityIntent)
            }
            image_02.id -> message = "Image 02"
            image_03.id -> message = "Image 03"
            image_04.id -> message = "Image 04"
            image_05.id -> message = "Image 05"
            image_06.id -> message = "Image 06"
            image_07.id -> message = "Image 07"
            image_08.id -> message = "Image 08"
            else -> "UNKNOWN PRESS"
        }
        Toast.makeText(applicationContext, "$message was pressed", Toast.LENGTH_SHORT).show()
    }
}