package com.example.diceapp_xml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var dice1: ImageView
    private lateinit var dice2: ImageView
    private lateinit var btnRoll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice1 = findViewById(R.id.dice1)
        dice2 = findViewById(R.id.dice2)
        btnRoll = findViewById(R.id.btnRoll)

        btnRoll.setOnClickListener {
            val value1 = (1..6).random()
            val value2 = (1..6).random()

            dice1.setImageResource(getDiceImage(value1))
            dice2.setImageResource(getDiceImage(value2))

            val message = if (value1 == value2) {
                "Selamat, anda dapat dadu double!"
            } else {
                "Anda belum beruntung!"
            }

            val rootLayout = findViewById<android.view.View>(R.id.rootLayout)

            val snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }

    private fun getDiceImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }
}