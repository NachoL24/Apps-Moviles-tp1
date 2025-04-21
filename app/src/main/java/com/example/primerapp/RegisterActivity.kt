package com.example.primerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatDelegate
import com.example.primerapp.databinding.RegisterBinding
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val themeMode = sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(themeMode)

        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NameInput.addTextChangedListener(createTextWatcher(binding.NameInputLayout))
        binding.EmailInput.addTextChangedListener(createTextWatcher(binding.EmailInputLayout))
        binding.PasswordInput.addTextChangedListener(createTextWatcher(binding.PasswordInputLayout))
        binding.PasswordConfirmInput.addTextChangedListener(createTextWatcher(binding.PasswordConfirmInputLayout))

        binding.button.setOnClickListener {
            val name = binding.NameInput.text
            val email = binding.EmailInput.text
            val password = binding.PasswordInput.text
            val passwordConfirm = binding.PasswordConfirmInput.text

            binding.NameInputLayout.error = null
            binding.EmailInputLayout.error = null
            binding.PasswordInputLayout.error = null
            binding.PasswordConfirmInputLayout.error = null

            if (name.isNullOrEmpty()) {
                binding.NameInputLayout.isErrorEnabled = true
                binding.NameInputLayout.error = "El nombre es obligatorio"
                binding.NameInput.requestFocus()
                return@setOnClickListener
            }

            if (email.isNullOrEmpty()) {
                binding.EmailInputLayout.isErrorEnabled = true
                binding.EmailInputLayout.error = "El email es obligatorio"
                binding.EmailInput.requestFocus()
                return@setOnClickListener
            }

            if (password.isNullOrEmpty()) {
                binding.PasswordInputLayout.isErrorEnabled = true
                binding.PasswordInputLayout.error = "La contraseña es obligatoria"
                binding.PasswordInput.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.PasswordInputLayout.isErrorEnabled = true
                binding.PasswordInputLayout.error = "La contraseña debe tener al menos 6 caracteres"
                binding.PasswordInput.requestFocus()
                return@setOnClickListener
            }

            if (passwordConfirm.isNullOrEmpty()) {
                binding.PasswordConfirmInputLayout.isErrorEnabled = true
                binding.PasswordConfirmInputLayout.error = "La confirmación de contraseña es obligatoria"
                binding.PasswordConfirmInput.requestFocus()
                return@setOnClickListener
            }

            if (password.toString() != passwordConfirm.toString()) {
                binding.PasswordConfirmInputLayout.isErrorEnabled = true
                binding.PasswordConfirmInputLayout.error = "Las contraseñas no coinciden"
                binding.PasswordConfirmInput.requestFocus()
                return@setOnClickListener
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnBack.setOnClickListener {
            if (isTaskRoot) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }

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
        }
    }

    private fun createTextWatcher(inputLayout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun saveThemePreference(mode: Int) {
        val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("theme_mode", mode)
        editor.apply()
    }
}