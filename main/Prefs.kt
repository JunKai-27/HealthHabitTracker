package com.example.healthhabittracker
import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val P = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    var skipWelcome: Boolean
        get() = P.getBoolean("skip_welcome", false)
        set(value) { P.edit().putBoolean("skip_welcome", value).apply() }
}