package com.example.primerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.primerapp.databinding.RegisterBinding
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Agregar TextWatchers para limpiar errores automáticamente
        binding.NameInput.addTextChangedListener(createTextWatcher(binding.NameInputLayout))
        binding.EmailInput.addTextChangedListener(createTextWatcher(binding.EmailInputLayout))
        binding.PasswordInput.addTextChangedListener(createTextWatcher(binding.PasswordInputLayout))
        binding.PasswordConfirmInput.addTextChangedListener(createTextWatcher(binding.PasswordConfirmInputLayout))

        binding.button.setOnClickListener {
            val name = binding.NameInput.text
            val email = binding.EmailInput.text
            val password = binding.PasswordInput.text
            val passwordConfirm = binding.PasswordConfirmInput.text

            // Limpiar errores previos
            binding.NameInputLayout.error = null
            binding.EmailInputLayout.error = null
            binding.PasswordInputLayout.error = null
            binding.PasswordConfirmInputLayout.error = null

            // Validar nombre
            if (name.isNullOrEmpty()) {
                binding.NameInputLayout.isErrorEnabled = true
                binding.NameInputLayout.error = "El nombre es obligatorio"
                binding.NameInput.requestFocus()
                return@setOnClickListener
            }

            // Validar email
            if (email.isNullOrEmpty()) {
                binding.EmailInputLayout.isErrorEnabled = true
                binding.EmailInputLayout.error = "El email es obligatorio"
                binding.EmailInput.requestFocus()
                return@setOnClickListener
            }

            // Validar contraseña
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

            // Validar confirmación de contraseña
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

            // Si todo está correcto, navegar al login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Crear un TextWatcher para limpiar errores automáticamente
    private fun createTextWatcher(inputLayout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    inputLayout.error = null // Eliminar el texto del error
                    inputLayout.isErrorEnabled = false // Eliminar el espacio reservado para el error
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        }
    }
}