package com.fionova.myfirstapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat

class RegistrationActivity : AppCompatActivity() {
    private var selectedButton: androidx.appcompat.widget.AppCompatButton? = null
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_registration)

        sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE)

        val button_number = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button_number)
        val button_email = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button_email)
        val input_email_number = findViewById<EditText>(R.id.input_email)
        val button_sign_up = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button_sign_up)
        val input_password = findViewById<EditText>(R.id.input_password)
        val input_password_again = findViewById<EditText>(R.id.input_password_again)

        selectedButton = button_number

        button_number.setOnClickListener {
            selectButton(button_number)
            input_email_number.hint = "Введите номер"
            input_email_number.inputType = InputType.TYPE_CLASS_PHONE
        }

        button_email.setOnClickListener {
            selectButton(button_email)
            input_email_number.hint = "Введите email"
            input_email_number.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        button_sign_up.setOnClickListener {
            var login: String? = null

            //Проверка email на содержание символа @
            val email = input_email_number.text.toString()
            if (selectedButton === button_email) {
                if (!email.contains("@")) {
                    Toast.makeText(this, "В email отсутствует знак @!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                login = email
            }
            //Проверка телефона на содержание символа +
            val number = input_email_number.text.toString()
            if (selectedButton === button_number) {
                if (!number.contains("+")) {
                    Toast.makeText(this, "В номере телефона отсутствует знак +!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                login = number
            }
            //Проверка пароля на количество символов (минимум 8)
            val password = input_password.text.toString()

            if (password.length < 8){
                Toast.makeText(this, "Пароль содержит менее 8 символов!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            //Проверка, равны ли строки, взятые из пароля и его повторения
            val password_again = input_password_again.text.toString()

            if (password != password_again){
                Toast.makeText(this, "Пароль неверный!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

//            with(sharedPreferences.edit()) {
//                if (selectedButton === button_email) {
//                    putString("email", email)
//                } else {
//                    putString("number", number)
//                }
//                putString("password", password)
//                apply()
//            }

//            with(sharedPreferences.edit()) {
//                putString("login", login)
//                putString("password", password)
//                apply()
//            }

            saveUserData(login, password)

            startActivity(Intent(this, ContentActivity::class.java))
            finish()
        }
    }

            private fun saveUserData(login: String?, password: String?) {
                with(sharedPreferences.edit()) {
                    putString("login", login)
                    putString("password", password)
                    apply()
                }
            }

            private fun selectButton(button: androidx.appcompat.widget.AppCompatButton){
                selectedButton?.setTextColor(ContextCompat.getColor(this, R.color.green))//getResources().getColor(R.color.green))
                selectedButton = button
                selectedButton?.setTextColor(ContextCompat.getColor(this, R.color.purple_500))
            }
    }

