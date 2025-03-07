package com.fionova.myfirstapp

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class RegisterFragment : Fragment() {
    private var selectedButton: androidx.appcompat.widget.AppCompatButton? = null
    private val sharedPreferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_registration, container, false)

        val navController = NavHostFragment.findNavController(this)

        val button_number = root.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button_number)
        val button_email = root.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button_email)
        val input_email_number = root.findViewById<EditText>(R.id.input_email)
        val button_sign_up = root.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button_sign_up)
        val input_password = root.findViewById<EditText>(R.id.input_password)
        val input_password_again = root.findViewById<EditText>(R.id.input_password_again)

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
                    Toast.makeText(requireContext(), "В email отсутствует знак @!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                login = email
            }
            //Проверка телефона на содержание символа +
            val number = input_email_number.text.toString()
            if (selectedButton === button_number) {
                if (!number.contains("+")) {
                    Toast.makeText(requireContext(), "В номере телефона отсутствует знак +!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                login = number
            }
            //Проверка пароля на количество символов (минимум 8)
            val password = input_password.text.toString()

            if (password.length < 8){
                Toast.makeText(requireContext(), "Пароль содержит менее 8 символов!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            //Проверка, равны ли строки, взятые из пароля и его повторения
            val password_again = input_password_again.text.toString()

            if (password != password_again){
                Toast.makeText(requireContext(), "Пароль неверный!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            saveUserData(login, password)

            navController.navigate(R.id.ContentActivity)
        }

        return root
    }

    private fun saveUserData(login: String?, password: String?) {
        with(sharedPreferences.edit()) {
            putString("login", login)
            putString("password", password)
            apply()
        }
    }

    private fun selectButton(button: androidx.appcompat.widget.AppCompatButton){
        selectedButton?.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))//getResources().getColor(R.color.green))
        selectedButton = button
        selectedButton?.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_500))
    }
}