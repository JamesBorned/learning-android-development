package com.fionova.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var selectedButton: androidx.appcompat.widget.AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val button_number: androidx.appcompat.widget.AppCompatButton = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button_number)
        val button_email: androidx.appcompat.widget.AppCompatButton = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button_email)
        val input_email_number: EditText = findViewById<EditText>(R.id.input_email)

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
    }

    private fun selectButton(button: androidx.appcompat.widget.AppCompatButton){
        selectedButton?.setTextColor(getResources().getColor(R.color.green))
        selectedButton = button
        selectedButton?.setTextColor(getResources().getColor(R.color.purple_500))
    }
}
