package com.example.shreyash.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var diceImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val roll: Button =findViewById(R.id.roll_button)
        roll.text="Lets Roll"
        roll.setOnClickListener {
     //       Toast.makeText(applicationContext,"hello",Toast.LENGTH_SHORT).show()
            rollDice()
        }
    }
    private fun rollDice(){
       diceImage = findViewById(R.id.dice_image)
        val randomInt = Random().nextInt(6) + 1
        //result.text=randomInt.toString()
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
    }
}
