package com.example.healthhabittracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthhabittracker.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private val prefs by lazy { Prefs(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // If user chose to skip, go straight to MainActivity
        if (prefs.skipWelcome) {
            startMainAndFinish()
            return
        }

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start tween animation defined in res/anim/rotate_scale_fade.xml
        binding.logoImage.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.rotate_scale_fade))

        binding.btnContinue.setOnClickListener {
            // Save the preference if checkbox selected
            prefs.skipWelcome = binding.cbSkip.isChecked
            startMainAndFinish()
        }
    }

    private fun startMainAndFinish() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
