package com.example.week3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSubmit = findViewById<Button>(R.id.btnSubmitQuiz)
        
        val rg1 = findViewById<RadioGroup>(R.id.rgQuestion1)
        val rg2 = findViewById<RadioGroup>(R.id.rgQuestion2)
        val rg3 = findViewById<RadioGroup>(R.id.rgQuestion3)
        val rg4 = findViewById<RadioGroup>(R.id.rgQuestion4)
        val rg5 = findViewById<RadioGroup>(R.id.rgQuestion5)

        btnSubmit.setOnClickListener {
            var score = 0
            
            // Correct Answers:
            // Q1: Paris (q1_opt3)
            // Q2: Mars (q2_opt2)
            // Q3: Pacific Ocean (q3_opt4)
            // Q4: William Shakespeare (q4_opt2)
            // Q5: Vatican City (q5_opt3)

            if (rg1.checkedRadioButtonId == R.id.q1_opt3) score++
            if (rg2.checkedRadioButtonId == R.id.q2_opt2) score++
            if (rg3.checkedRadioButtonId == R.id.q3_opt4) score++
            if (rg4.checkedRadioButtonId == R.id.q4_opt2) score++
            if (rg5.checkedRadioButtonId == R.id.q5_opt3) score++

            val intent = Intent(this, QuizResultActivity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL", 5)
            startActivity(intent)
            finish()
        }
    }
}