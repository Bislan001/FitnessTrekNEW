package com.example.fitnesssensor

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CardActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        supportActionBar?.hide()

        val bmi = findViewById<ImageView>(R.id.imgBMI)
        val sensor = findViewById<ImageView>(R.id.imagePedoMeter)
        val calendar = findViewById<ImageView>(R.id.imageTips)
        val yoga = findViewById<ImageView>(R.id.imageYoga)
        bmi.setOnClickListener {
            val i = Intent(this, BMIactivity::class.java)
            startActivity(i)
        }
        sensor.setOnClickListener {
            val i2 = Intent(this, PedoSensor1::class.java)
            startActivity(i2)
        }
        calendar.setOnClickListener {
            val i3 = Intent(this, Calendar::class.java)
            startActivity(i3)
        }
        yoga.setOnClickListener {
            val i4 = Intent(this, Yoga::class.java)
            startActivity(i4)
        }
    }
}