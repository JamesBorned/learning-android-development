package com.fionova.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController

class ContentActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val bottom_nav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_nav)

        bottom_nav.setupWithNavController(navController)
    }
}