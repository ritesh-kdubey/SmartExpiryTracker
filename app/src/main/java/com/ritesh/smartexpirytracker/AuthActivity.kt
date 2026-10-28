package com.ritesh.smartexpirytracker

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash Screen
        setContentView(R.layout.activity_auth)

        // Splash → Welcome
        Handler(Looper.getMainLooper()).postDelayed({

            setContentView(R.layout.welcome)

            // Welcome → Login
            val btnLogin = findViewById<TextView>(R.id.tvbtnLogin)

            btnLogin.setOnClickListener {

                // Login Screen
                setContentView(R.layout.login)

                // Login → Sign Up
                val signUp = findViewById<TextView>(R.id.tvSignUp)

                signUp.setOnClickListener {
                    setContentView(R.layout.signup)
                }
            }

        }, 20000)
    }
}



