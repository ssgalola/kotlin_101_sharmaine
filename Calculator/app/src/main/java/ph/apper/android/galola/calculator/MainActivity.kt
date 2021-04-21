package ph.apper.android.galola.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var isNewOp = true
    var dot = false

    fun clickNumber(view: View) {
        if(isNewOp) {
            tvShowNumber.text = ""
        }

        isNewOp = false
        val btnSelect = view as Button
        var btnClickValue:String = tvShowNumber.text.toString()

        when(btnSelect.id) {
            btn0.id-> btnClickValue += "0"
            btn1.id-> btnClickValue += "1"
            btn2.id-> btnClickValue += "2"
            btn3.id-> btnClickValue += "3"
            btn4.id-> btnClickValue += "4"
            btn5.id-> btnClickValue += "5"
            btn6.id-> btnClickValue += "6"
            btn7.id-> btnClickValue += "7"
            btn8.id-> btnClickValue += "8"
            btn9.id-> btnClickValue += "9"
            btnDot.id-> {
                if(dot==false) {
                    btnClickValue += "."
                }
                dot=true
            }
            btnPlusMinus.id-> btnClickValue = "-$btnClickValue"
        }
        tvShowNumber.text = btnClickValue
    }
    var op = ""
    var oldNumber = ""

    fun clickOperation(view: View) {
        val btnSelect = view as Button

        when(btnSelect.id) {
            btnMul.id-> op = "X"
            btnDiv.id-> op = "÷"
            btnSub.id-> op = "-"
            btnAdd.id-> op = "+"
        }
        oldNumber = tvShowNumber.text.toString()
        isNewOp = true
        dot = false
    }

    fun clickEquals(view: View) {
        val newNumber = tvShowNumber.text.toString()
        var finalNumber:Double ?= null
        when(op) {
            "X"-> finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            "÷"-> finalNumber = oldNumber.toDouble() / newNumber.toDouble()
            "-"-> finalNumber = oldNumber.toDouble() - newNumber.toDouble()
            "+"-> finalNumber = oldNumber.toDouble() + newNumber.toDouble()
            "" -> finalNumber = newNumber.toDouble()
        }
        tvShowNumber.text = finalNumber.toString()
        isNewOp = true
    }

    fun clickPercent(view: View) {
        val number = (tvShowNumber.text.toString().toDouble())/100
        tvShowNumber.text = number.toString()
        isNewOp = true
    }

    fun clickClearEntry(view: View) {
        tvShowNumber.text = ""
        isNewOp = true
        dot = false
    }

    fun clickClear(view: View) {
        val removedLast = tvShowNumber.text.toString().dropLast(1)
        tvShowNumber.text = removedLast
    }
}
