package com.example.lab8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculate(view: View) {
        var finalCalc : Int = 0

        val numsToUse = mutableListOf<Int>()

        //radio buttons
        val calcType = radioGroup.checkedRadioButtonId

        if (calcType == -1){
            //Snackbar
            val calcSnackbar = Snackbar.make(root_layout, "Please select a calculation", Snackbar.LENGTH_SHORT)
            calcSnackbar.show()
        } else {
            finalCalc = findViewById<RadioButton>(calcType).text.toString().toInt()

            //checkboxes
            if (checkBox1.isChecked) {
                numsToUse.add(checkBox1.text.toString().toInt())
            }
            if (checkBox2.isChecked) {
                numsToUse.add(checkBox2.text.toString().toInt())
            }
            if (checkBox3.isChecked) {
                numsToUse.add(checkBox3.text.toString().toInt())
            }
            if (checkBox4.isChecked) {
                numsToUse.add(checkBox4.text.toString().toInt())
            }

            //spinner
            val color = spinner.selectedItem
            if (color == "black") {
                calcTextView.setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))
            }
            else if (color == "green") {
                calcTextView.setTextColor(ResourcesCompat.getColor(resources, R.color.green, null))
            }
            else if (color == "blue") {
                calcTextView.setTextColor(ResourcesCompat.getColor(resources, R.color.blue, null))
            }

            //compute calc
            if (calcType == 0) {
                finalCalc = numsToUse.sum()
            }
            else if (calcType == 1) {
                finalCalc = numsToUse.reduce{acc, i->acc*i}
            }

            //switch
            if (signSwitch.isChecked) {
                finalCalc = -finalCalc
            }

            //textview
            calcTextView.text = finalCalc.toString()
        }
    }
}