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

        binding.PasswordInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrEmpty()){
                    binding.PasswordInput.error = null
                    binding.PasswordInputLayout.isEndIconVisible = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        binding.button.setOnClickListener{
            val name = binding.NameInput.text
            val email = binding.EmailInput.text
            val password = binding.PasswordInput.text
            val passwordConfirm = binding.PasswordConfirmInput.text

            binding.NameInput.error = null
            binding.EmailInput.error = null
            binding.PasswordInput.error = null
            binding.PasswordConfirmInput.error = null


            if (name.isNullOrEmpty()) {
                binding.NameInput.error = "El nombre es obligatorio"
                binding.NameInput.requestFocus()
                binding.NameInputLayout.isEndIconVisible = false
                return@setOnClickListener
            }

            if (email.isNullOrEmpty()) {
                binding.EmailInput.error = "El email es obligatorio"
                binding.EmailInput.requestFocus()
                binding.NameInputLayout.isEndIconVisible = false
                return@setOnClickListener
            }

            if (password.isNullOrEmpty()) {
                binding.PasswordInput.error = "La contraseña es obligatoria"
                binding.PasswordInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
                binding.PasswordInput.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6){
                binding.PasswordInput.error = "La contraseña debe tener al menos 6 caracteres"
                binding.PasswordInputLayout.isEndIconVisible = false
                binding.PasswordInput.requestFocus()
                binding.PasswordInputLayout.isEndIconVisible = true
                return@setOnClickListener
            }

            if (passwordConfirm.isNullOrEmpty()) {
                binding.PasswordConfirmInput.error = "La confirmación de contraseña es obligatoria"
                binding.NameInputLayout.isEndIconVisible = false
                binding.PasswordConfirmInput.requestFocus()
                binding.NameInputLayout.isEndIconVisible = true
                return@setOnClickListener
            }

            if (!password.toString().equals(passwordConfirm.toString())) {
                binding.PasswordConfirmInput.error = "Las contraseñas no coinciden" + password + " " + passwordConfirm
                binding.NameInputLayout.isEndIconVisible = false
                binding.PasswordConfirmInput.requestFocus()
                binding.NameInputLayout.isEndIconVisible = true
                return@setOnClickListener
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}