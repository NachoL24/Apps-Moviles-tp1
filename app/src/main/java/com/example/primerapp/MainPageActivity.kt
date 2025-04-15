package com.example.primerapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primerapp.databinding.MainpageBinding

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: MainpageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = "Juan Torres"
        val welcome = getString(R.string.welcome_message, userName)
        binding.tvWelcomeTitle.text = welcome

        // Mostrar input si se selecciona "Otra"
        binding.cbOther.setOnCheckedChangeListener { _, isChecked ->
            binding.etOtherSpecify.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        // Autoexclusion entre android y apple
        binding.rbAndroid.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.rbApple.isChecked = false
        }

        binding.rbApple.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.rbAndroid.isChecked = false
        }

        // Boton aceptar
        binding.btnAccept.setOnClickListener {
            // Validar plataforma
            val platformSelected = binding.rbAndroid.isChecked || binding.rbApple.isChecked
            if (!platformSelected) {
                Toast.makeText(this, "Debe seleccionar una plataforma.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar al menos una preferencia
            val prefsChecked = listOf(
                binding.cbProgramming,
                binding.cbNetworks,
                binding.cbHardware,
                binding.cbSecurity,
                binding.cbOther
            ).any { it.isChecked }

            if (!prefsChecked) {
                Toast.makeText(this, "Debe seleccionar al menos una preferencia.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Si esta todo ok
            Toast.makeText(this, "Información guardada correctamente.", Toast.LENGTH_SHORT).show()
        }
    }
}
