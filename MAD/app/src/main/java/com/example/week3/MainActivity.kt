package com.example.week3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Welcome Toast from Login (Optional, kept from previous context)
        val username = intent.getStringExtra("USERNAME")
        if (username != null) {
            Toast.makeText(this, "Welcome $username!", Toast.LENGTH_LONG).show()
        }

        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val btnGoToQuiz = findViewById<Button>(R.id.btnGoToQuiz)

        btnShow.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val heightStr = etHeight.text.toString()
            val weightStr = etWeight.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("NAME", name)
                    putExtra("AGE", age)
                    putExtra("HEIGHT", heightStr.toDouble())
                    putExtra("WEIGHT", weightStr.toDouble())
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        btnGoToQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}