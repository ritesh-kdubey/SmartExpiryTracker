package com.ritesh.smartexpirytracker

import android.content.Context

class UserPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(
        "SmartExpiryUsers",
        Context.MODE_PRIVATE
    )

    fun registerUser(name: String, email: String, password: String) {
        preferences.edit()
            .putString("USER_NAME", name)
            .putString("USER_EMAIL", email)
            .putString("USER_PASSWORD", password)
            .apply()
    }

    fun checkLogin(email: String, password: String): Boolean {
        val savedEmail = preferences.getString("USER_EMAIL", null)
        val savedPassword = preferences.getString("USER_PASSWORD", null)

        return email == savedEmail && password == savedPassword
    }

    fun getUserName(): String {
        return preferences.getString("USER_NAME", "") ?: ""
    }

    fun isRegistered(): Boolean {
        return preferences.contains("USER_EMAIL")
    }

    fun logout() {
        preferences.edit()
            .remove("USER_EMAIL")
            .remove("USER_PASSWORD")
            .apply()
    }
}
