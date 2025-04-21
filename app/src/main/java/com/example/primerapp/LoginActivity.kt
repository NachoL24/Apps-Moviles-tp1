package com.example.primerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.primerapp.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val verifyUser = "Juan Torres"
        val verifyPass = "1234utn"
        super.onCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()

            // Limpiar errores previos
            binding.usernameLayout.error = null
            binding.passwordLayout.error = null

            if (username.isEmpty()) {
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
                // Navegar a la pantalla principal
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
}