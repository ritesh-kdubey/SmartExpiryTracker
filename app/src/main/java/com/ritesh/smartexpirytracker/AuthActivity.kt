package com.ritesh.smartexpirytracker

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplashScreen()
    }

    // 1. Splash Screen
    private fun showSplashScreen() {

        setContentView(R.layout.activity_splash_screen)

        handler.postDelayed({
            if (!isFinishing) {
                showWelcomeScreen()
            }
        }, 2000L)
    }

    // 2. Welcome Screen
    private fun showWelcomeScreen() {

        setContentView(R.layout.activity_welcome)

        findViewById<View>(R.id.btnGetStart).setOnClickListener {
            showLoginScreen()
        }

        findViewById<TextView>(R.id.tvbtnLogin).setOnClickListener {
            showLoginScreen()
        }
    }

    // 3. Login Screen
    private fun showLoginScreen() {

        setContentView(R.layout.activity_login)

        val userPreferences = UserPreferences(this)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<View>(R.id.btnLogin)



        // Signup Navigation
        findViewById<TextView>(R.id.tvSignUp).setOnClickListener {
            showSignupScreen()
        }
        // Forgot Password Navigation
        findViewById<TextView>(R.id.tvForgetPassword).setOnClickListener {
            showForgetPasswordScreen()

        }

        // Login Button
        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            when {

                email.isEmpty() -> {
                    etEmail.error = "Enter your email"
                }

                !android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email).matches() -> {
                    etEmail.error = "Enter a valid email"
                }

                password.isEmpty() -> {
                    etPassword.error = "Enter your password"
                }

                userPreferences.checkLogin(email, password) -> {

                    Toast.makeText(
                        this,
                        "Login Successful!",
                        Toast.LENGTH_SHORT
                    ).show()

                    showDashboardScreen()
                }

                else -> {
                    Toast.makeText(
                        this,
                        "Invalid Email or Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // 4. Signup Screen
    private fun showSignupScreen() {

        setContentView(R.layout.signup)

        val userPreferences = UserPreferences(this)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword =
            findViewById<EditText>(R.id.etCreatePassword)

        val btnSignUp = findViewById<View>(R.id.btnSignUp)

        // Back Button
        findViewById<View>(R.id.btnBack).setOnClickListener {
            showLoginScreen()
        }

        // Login Navigation
        findViewById<TextView>(R.id.tvLogin).setOnClickListener {
            showLoginScreen()
        }

        // Signup Button
        btnSignUp.setOnClickListener {

            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword =
                etConfirmPassword.text.toString()

            when {

                name.isEmpty() -> {
                    etName.error = "Enter your name"
                }

                !android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email).matches() -> {
                    etEmail.error = "Enter a valid email"
                }

                password.length < 6 -> {
                    etPassword.error =
                        "Password must be at least 6 characters"
                }

                password != confirmPassword -> {
                    etConfirmPassword.error =
                        "Passwords do not match"
                }

                userPreferences.isRegistered() -> {
                    etEmail.error =
                        "An account is already registered"
                }

                else -> {

                    userPreferences.registerUser(
                        name,
                        email,
                        password
                    )

                    Toast.makeText(
                        this,
                        "Account created successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                    showLoginScreen()
                }
            }
        }
    }

    // 5. Dashboard Screen
    private fun showDashboardScreen() {

        setContentView(R.layout.activity_main_dashboard)

    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private fun showAccountCreatedScreen() {

        setContentView(R.layout.activity_created_successfully)

        findViewById<View>(R.id.btnGoToLogin).setOnClickListener {
            showLoginScreen()
        }
    }
    // Forgot Password Screen
    private fun showForgetPasswordScreen() {

        setContentView(R.layout.forgetpasswordscreen)

        // Back Button
        findViewById<View>(R.id.btnBack).setOnClickListener {
            showLoginScreen()
        }

        // Back to Login
        findViewById<TextView>(R.id.tvBackToLogin).setOnClickListener {
            showLoginScreen()
        }
    }
}

