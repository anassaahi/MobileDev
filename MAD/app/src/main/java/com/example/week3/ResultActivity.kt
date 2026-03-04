package com.example.week3

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("NAME")
        val age = intent.getStringExtra("AGE")
        val heightCm = intent.getDoubleExtra("HEIGHT", 0.0)
        val weightKg = intent.getDoubleExtra("WEIGHT", 0.0)

        val tvUserDetails = findViewById<TextView>(R.id.tvUserDetails)
        val tvBmiValue = findViewById<TextView>(R.id.tvBmiValue)
        val tvBmiIndicator = findViewById<TextView>(R.id.tvBmiIndicator)
        val btnBack = findViewById<Button>(R.id.btnBack)

        tvUserDetails.text = "Name: $name\nAge: $age"

        if (heightCm > 0) {
            val heightM = heightCm / 100
            val bmi = weightKg / (heightM * heightM)
            val bmiFormatted = String.format("%.2f", bmi)
            tvBmiValue.text = bmiFormatted

            when {
                bmi < 18.5 -> {
                    tvBmiIndicator.text = "Underweight"
                    tvBmiIndicator.setTextColor(Color.BLUE)
                }
                bmi in 18.5..24.9 -> {
                    tvBmiIndicator.text = "Normal"
                    tvBmiIndicator.setTextColor(Color.GREEN)
                }
                else -> {
                    tvBmiIndicator.text = "Overweight"
                    tvBmiIndicator.setTextColor(Color.RED)
                }
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}