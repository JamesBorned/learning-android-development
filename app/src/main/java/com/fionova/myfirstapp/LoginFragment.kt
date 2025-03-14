package com.fionova.myfirstapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val navController = NavHostFragment.findNavController(this)
        sharedPreferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)

        val input_login = root.findViewById<EditText>(R.id.input_login)
        val input_password_login = root.findViewById<EditText>(R.id.input_password)
        val auto_login_check_box = root.findViewById<CheckBox>(R.id.checkbox)
        val login_button = root.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.button)

        login_button.setOnClickListener {
            val login = input_login.text.toString()
            val password = input_password_login.text.toString()
            val autoLogin = auto_login_check_box.isChecked

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(),"Заполнены не все поля!",Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            val auth = FirebaseAuth.getInstance()

            auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("userAuthenticated", true)
                    editor.apply()

                    setAutoLogin(autoLogin)

                    navController.navigate(R.id.Fragment_1)
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
//            if (login == getLogin() && password == getPassword()) {
//                setAutoLogin(autoLogin)
//
//                navController.navigate(R.id.Fragment_1)
//            }
//            else {
//                Toast.makeText(requireContext(), "Неверные пароль или email!", Toast.LENGTH_SHORT).show()
//            }
        }

        return root
    }

    fun getLogin(): String? = sharedPreferences.getString("login", null)
    fun getPassword(): String? = sharedPreferences.getString("password", null)

    fun setAutoLogin(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("autoLogin", enabled).apply()
    }
}