package com.fionova.myfirstapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.isIndeterminate = true

        val sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE)

        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        val autoLogin = sharedPreferences.getBoolean("autoLogin", false)

        if (email != null && password != null) {
            if (autoLogin) {
                startActivity(Intent(this, ContentActivity::class.java))
            }
            else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        else {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }
}