package com.fionova.myfirstapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class SplashFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        val navController = NavHostFragment.findNavController(this)
        val sharedPreferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)

        val progressBar = root.findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.isIndeterminate = true

        val login = sharedPreferences.getString("login", null)
        val password = sharedPreferences.getString("password", null)
        val autoLogin = sharedPreferences.getBoolean("autoLogin", false)

        if (login != null && password != null) {
            if (autoLogin) {
                navController.navigate(R.id.Fragment_1)
            } else {
                navController.navigate(R.id.LoginFragment)
            }
        } else {
            navController.navigate(R.id.RegisterFragment)
        }
    return root
    }
}