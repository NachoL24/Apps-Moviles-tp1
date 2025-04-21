package com.example.primerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.primerapp.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val themeMode = sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(themeMode)

        val verifyUser = "Juan Torres"
        val verifyPass = "1234utn"
        super.onCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnThemeToggle.setOnClickListener {
            val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
            val currentMode = sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            
            val newMode = when (currentMode) {
                AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
                AppCompatDelegate.MODE_NIGHT_NO -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                else -> AppCompatDelegate.MODE_NIGHT_YES
            }
            
            AppCompatDelegate.setDefaultNightMode(newMode)
            saveThemePreference(newMode)
            delegate.applyDayNight()
        }

        binding.inputUsername.addTextChangedListener(createTextWatcher(binding.usernameLayout))
        binding.inputPassword.addTextChangedListener(createTextWatcher(binding.passwordLayout))

        binding.buttonLogin.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()

            binding.usernameLayout.error = null
            binding.passwordLayout.error = null

            if (username.isEmpty()) {
                binding.usernameLayout.isErrorEnabled = true
                binding.usernameLayout.error = "El nombre de usuario es requerido"
                binding.inputUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.passwordLayout.error = "La contraseña es requerida"
                binding.inputPassword.requestFocus()
                return@setOnClickListener
            }

            if (username != verifyUser || password != verifyPass) {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                Toast.makeText(this, "¡Inicio de sesión exitoso!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.registerLink.setOnClickListener {
             val intent = Intent(this, RegisterActivity::class.java)
             startActivity(intent)
             finish()
        }

        binding.forgotPassword.setOnClickListener {
            Toast.makeText(this, "Ir a la pantalla de Olvidé Contraseña", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para guardar la preferencia del tema
    private fun saveThemePreference(mode: Int) {
        val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("theme_mode", mode)
        editor.apply()
    }

    // Crear un TextWatcher para limpiar errores automáticamente
    private fun createTextWatcher(inputLayout: com.google.android.material.textfield.TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    inputLayout.error = null
                    inputLayout.isErrorEnabled = false
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        }
    }
}