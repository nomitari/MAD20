package com.example.stroop

import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.content.res.ResourcesCompat
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{
    //  https://stackoverflow.com/questions/16769654/how-to-use-onsaveinstancestate-and-onrestoreinstancestate
    override fun onSaveInstanceState(savedInstanceState: Bundle)
    {
        // Save entered code
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("numRounds", rounds)
        savedInstanceState.putInt("results", resultSum)
        //  https://www.youtube.com/watch?v=LMYQS1dqfo8&ab_channel=CodinginFlow
        savedInstanceState.putLong("millisLeft", timeLeft)
        savedInstanceState.putBoolean("timerRunning", timerRunning)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle)
    {
        super.onRestoreInstanceState(savedInstanceState)

        timeLeft = savedInstanceState.getLong("millisLeft")
        timerRunning = savedInstanceState.getBoolean("timerRunning")
        rounds = savedInstanceState.getInt("numRounds")
        resultSum = savedInstanceState.getInt("results")

        if (timerRunning)
        {
            timer.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        exitButton.isVisible = false
    }

    private val colorStrings = listOf("red", "orange", "yellow", "green", "blue", "purple", "pink", "black", "gray", "brown")
    private val colors = listOf(R.color.red, R.color.orange, R.color.yellow, R.color.green, R.color.blue, R.color.purple, R.color.pink, R.color.black, R.color.gray, R.color.brown)

    var resultSum : Int = 0
    var rounds : Int = 0
    var timeLeft : Long = 10000
    var timerRunning = false

    //  https://kotlinlang.org/docs/reference/properties.html
    private lateinit var correctSquare : ImageButton

    //  https://developer.android.com/reference/kotlin/android/os/CountDownTimer
    private val timer = object : CountDownTimer(timeLeft, 800)
    {
        override fun onTick(millisUntilFinished: Long)
        {
            timeLeft = millisUntilFinished
            timerRunning = true
            playRound()
        }

        override fun onFinish()
        {
            timerRunning = false
            showResults()
        }
    }

    //  called on click exit from inside game
    fun exit(view: View)
    {
        //  set button to "PLAY"
        playButton.text = getString(R.string.stroopButton)
        playButton.isEnabled  = true

        timer.cancel()
        textView.text = getString(R.string.instructions)

        exitButton.isVisible = false

    }

    //  called on click play, controls timer
    //  https://developer.android.com/reference/kotlin/android/os/CountDownTimer
    fun play(view: View)
    {
        resultSum = 0
        exitButton.isVisible = true
        timer.start()
        timerRunning = true
    }

    fun playRound()
    {
        if (rounds == 10)
        {
            timer.cancel()
            showResults()
            rounds = 0
            return
        }

        timerRunning = true
        rounds += 1
        textView.text = getString(R.string.none)

        val wordText = colorStrings.random()
        val wordColor = colors.random()

        playButton.text = wordText
        //  https://stackoverflow.com/questions/40813240/getcolorint-is-deprecated-how-i-can-use-getcolorint-theme-theme
        playButton.setTextColor(ResourcesCompat.getColor(resources, wordColor, null))

        correctSquare = listOf(topLeftColor, topRightColor, bottomLeftColor, bottomRightColor).random()

        //  set random colors for all square buttons
        randomizeTiles(correctSquare, wordText)
    }

    private fun randomizeTiles(correctSquare: ImageButton, colorString: String)
    {
        val colorButtons = listOf<ImageButton>(topLeftColor, topRightColor, bottomLeftColor, bottomRightColor)
        val usedColors = mutableListOf<String>(colorString)

        for (square in colorButtons)
        {
            //  don't want to repeat colors in a round
            //  https://stackoverflow.com/questions/47850156/get-a-random-item-from-list-using-kotlin-streams
            var randColor = colorStrings.random()
            while (usedColors.contains(randColor))
            {
                randColor = colorStrings.random()
            }
            usedColors.add(randColor)
            //  https://superkotlin.com/kotlin-when-statement/
            when(randColor)
            {
                "black" -> square.setImageResource(R.drawable.black)
                "blue" -> square.setImageResource(R.drawable.blue)
                "brown" -> square.setImageResource(R.drawable.brown)
                "gray" -> square.setImageResource(R.drawable.gray)
                "green" -> square.setImageResource(R.drawable.green)
                "orange" -> square.setImageResource(R.drawable.orange)
                "pink" -> square.setImageResource(R.drawable.pink)
                "purple" -> square.setImageResource(R.drawable.purple)
                "red" -> square.setImageResource(R.drawable.red)
                "yellow" -> square.setImageResource(R.drawable.yellow)
            }

            //  if at correct square
            //  https://stackoverflow.com/questions/37618738/how-to-check-if-a-lateinit-variable-has-been-initialized
            if (this::correctSquare.isInitialized) {
                if (square == correctSquare) {
                    //  set random square to be correct answer
                    when (colorString) {
                        "black" -> square.setImageResource(R.drawable.black)
                        "blue" -> square.setImageResource(R.drawable.blue)
                        "brown" -> square.setImageResource(R.drawable.brown)
                        "gray" -> square.setImageResource(R.drawable.gray)
                        "green" -> square.setImageResource(R.drawable.green)
                        "orange" -> square.setImageResource(R.drawable.orange)
                        "pink" -> square.setImageResource(R.drawable.pink)
                        "purple" -> square.setImageResource(R.drawable.purple)
                        "red" -> square.setImageResource(R.drawable.red)
                        "yellow" -> square.setImageResource(R.drawable.yellow)
                    }
                }
            }
        }
    }

    fun colorClick(view: View)
    {
        //  https://stackoverflow.com/questions/62117188/how-to-get-parameter-from-view-in-onclick-function-in-kotlin
        if (view.id == correctSquare.id)
        {
            resultSum += 1
        }
    }

    fun showResults()
    {
        timerRunning = false
        //  sum of positive results * 10 to get percentage
        val score = resultSum * 10

        //  show score and change button to "play again"
        textView.text = getString(R.string.score).plus(score.toString()).plus("%")

        playButton.isEnabled = true
        playButton.text = getString(R.string.stroopButtonAgain)

        exitButton.isEnabled = true
        exitButton.isVisible = true

    }
}