package com.example.lab9

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_lab9.*

class TheaterActivity : AppCompatActivity() {
        private var theaterName : String? = null
        private var theaterUrl : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab9)
        setSupportActionBar(findViewById(R.id.toolbar))

        theaterName = intent.getStringExtra("theaterName")
        theaterUrl = intent.getStringExtra("theaterUrl")

        theaterName?.let { Log.i("name received", it) };
        theaterUrl?.let { Log.i("url received", it) };

        theaterName?.let {theaterTextView.text = "You should watch a movie at $theaterName "}

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            loadWebSite()
        }

        //using Kotlin extension plugin
        //fab.setOnClickListener {
        //    loadWebSite()
        //}
    }

    private fun loadWebSite(){
        //create intent

        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = theaterUrl?.let{Uri.parse(theaterUrl)}

        //var intent = Intent().apply {
        //    action = Intent.ACTION_VIEW;
        //    data = theaterUrl?.let{Uri.parse(theaterUrl)};
        //}

        //val intent = Intent(Intent.ACTION_VIEW, theaterUrl?.let{Uri.parse(theaterUrl)})

        // Verify that the intent will resolve to an activity
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val data = Intent()
        data.putExtra("feedback", feedbackEditText.text.toString())
        setResult(Activity.RESULT_OK, data) //must be set before super.onBackPressed()
        super.onBackPressed()
        finish()
    }
}
