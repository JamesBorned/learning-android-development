package com.fionova.myfirstapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE)

        val input_login = findViewById<EditText>(R.id.input_login)
        val input_password_login = findViewById<EditText>(R.id.input_password)
        val auto_login_check_box = findViewById<CheckBox>(R.id.checkbox)
        val login_button = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button)

        login_button.setOnClickListener {
            val login = input_login.text.toString()
            val password = input_password_login.text.toString()
            val autoLogin = auto_login_check_box.isChecked

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполнены не все поля!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (login == getLogin() && password == getPassword()) {
                setAutoLogin(autoLogin)
                startActivity(Intent(this, ContentActivity::class.java))
                finish()
            }
            else {
                Toast.makeText(this, "Неверные пароль или email!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getLogin(): String? = sharedPreferences.getString("login", null)
    fun getPassword(): String? = sharedPreferences.getString("password", null)

    fun setAutoLogin(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("autoLogin", enabled).apply()
    }

    fun isAutoLoginEnabled(): Boolean = sharedPreferences.getBoolean("autoLogin", false)
}