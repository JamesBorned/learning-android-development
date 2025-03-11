package com.fionova.myfirstapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContentActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var sharedPreferences: SharedPreferences
    private var isFragment1Visible = false
    private var isFragment2Visible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottomNav = findViewById(R.id.bottom_nav)
        sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            isFragment1Visible = destination.id == R.id.Fragment_1
            isFragment2Visible = destination.id == R.id.Fragment_2

            updateBottomNavVisibility()
        }
    }

    private fun updateBottomNavVisibility() {
        bottomNav.visibility = if (isFragment1Visible || isFragment2Visible) View.VISIBLE else View.GONE

        bottomNav.setupWithNavController(navController)
    }
}

