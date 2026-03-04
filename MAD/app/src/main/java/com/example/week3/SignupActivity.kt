package com.example.week3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        
        // Handle window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup_text)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val email = findViewById<EditText>(R.id.signup_email)
        val username = findViewById<EditText>(R.id.signup_username)
        val password = findViewById<EditText>(R.id.signup_password)
        val confirmPassword = findViewById<EditText>(R.id.signup_confirm_password)
        val signupButton = findViewById<Button>(R.id.signup_button)

        signupButton.setOnClickListener {
            val emailText = email.text.toString().trim()
            val userText = username.text.toString().trim()
            val passText = password.text.toString()
            val confirmPassText = confirmPassword.text.toString()

            if (emailText.isEmpty() || userText.isEmpty() || passText.isEmpty() || confirmPassText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                email.error = "Invalid email format"
                return@setOnClickListener
            }

            if (passText.length < 8) {
                password.error = "Password must be at least 8 characters"
                return@setOnClickListener
            }

            if (passText != confirmPassText) {
                confirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Save data to SharedPreferences (XML file)
            val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("USER_$userText", passText)
            editor.apply()

            Toast.makeText(this, "Signup Successful!", Toast.LENGTH_SHORT).show()
            // Return to Login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}