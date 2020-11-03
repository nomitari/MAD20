package com.example.lab7

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sayHello(view: View) {
        val helloText = findViewById<TextView>(R.id.textMessage)
        val editName = findViewById<EditText>(R.id.editTextName).text
        helloText.setText("Hello " + editName + "!")

        val imageHello = findViewById<ImageView>(R.id.imageView)
        imageHello.setImageResource(R.drawable.hello)
    }
}