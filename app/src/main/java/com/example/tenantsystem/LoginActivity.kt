package com.example.tenantmanagement

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantsystem.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Email sent from RegisterActivity (null when the app opens normally)
        val registeredEmail = intent.getStringExtra("EMAIL")
        if (registeredEmail != null) {
            binding.emailEditText.setText(registeredEmail)
        }

        // Explicit Intent: Login -> MainActivity (carries the email: Try it yourself #2)
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra("EMAIL", email)
            startActivity(mainIntent)
            finish()
        }

        // Explicit Intent: Login -> RegisterActivity
        binding.registerTextView.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        // Implicit Intent: open a website
        binding.helpTextView.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.strathmore.edu"))
            startActivity(webIntent)
        }
    }
}