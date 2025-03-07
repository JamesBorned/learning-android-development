package com.fionova.myfirstapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class SplashFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        val navController = NavHostFragment.findNavController(this)
        val sharedPreferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)

        if (sharedPreferences.getString("type", "null")!="null")
            if (sharedPreferences.getBoolean("autoLogin", false)){
                navController.navigate(R.id.Fragment_1)
            }
            else {
                navController.navigate(R.id.LoginFragment)
            }
        else {
            navController.navigate(R.id.RegisterFragment)
        }

        return root
    }
}