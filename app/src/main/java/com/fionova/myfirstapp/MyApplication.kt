package com.fionova.myfirstapp

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this) // Initialize Firebase here
        Log.d("FIREBASE", "INIT APPLICATION")
    }
}

