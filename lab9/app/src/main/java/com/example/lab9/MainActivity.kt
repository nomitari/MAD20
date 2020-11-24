package com.example.lab9

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var theater = Theater()
    private var selectedLocationPosition = 0
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //event listener
        locationButton.setOnClickListener {
            selectedLocationPosition = spinner.selectedItemPosition
            theater.suggestTheater(selectedLocationPosition)
            Log.i("theater suggested", theater.name);
            Log.i("url suggested", theater.url);

            //create intent
            val intent = Intent(this, TheaterActivity::class.java)
            intent.putExtra("theaterName", theater.name)
            intent.putExtra("theaterURL", theater.url)

            //val intent = Intent(this, TheaterActivity::class.java).apply{
            //    putExtra("theaterName", theater.name);
            //    putExtra("theaterURL", theater.url);
            //}
            //startActivity(intent)

            //starting an intent that will pass data back
            startActivityForResult(intent, REQUEST_CODE)
            //startActivity(intent)
        }
    }

    fun getMovie(view: View) {

        var movieType : CharSequence = ""
        var movie = ""

        //radio buttons
        val movieId = radioGroup.checkedRadioButtonId

        if (movieId == -1){
            //Snackbar
            val movieSnackbar = Snackbar.make(root_layout, "Please select a movie type", Snackbar.LENGTH_SHORT)
            movieSnackbar.show()
        } else {
            movieType = findViewById<RadioButton>(movieId).text

            //spinner
            val location = "at " + spinner.selectedItem

            //switch
            if (topRatedSwitch.isChecked) {
                if (movieType == "narrative film") {
                    movie = "Freaky"
                }
                else {
                    movie = "Zappa"
                }
            }
            else {
                if (movieType == "narrative film") {
                    movie = "Tenet"
                }
                else {
                    movie = "Zappa"
                }
            }

            //textview
            messageTextView.text = "You should watch $movie"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
          reviewTextView.setText(data?.let{data.getStringExtra("feedback")})
        }
    }
}
