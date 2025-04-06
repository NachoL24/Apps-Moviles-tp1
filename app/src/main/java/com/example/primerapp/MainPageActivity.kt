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

        // Mensaje de bienvenida para Juan Torres
        val userName = "Juan Torres"
        val welcome = getString(R.string.welcome_message, userName)
        binding.tvWelcomeTitle.text = welcome

        binding.cbOther.setOnCheckedChangeListener { _, isChecked ->
            binding.etOtherSpecify.visibility = if (isChecked) View.VISIBLE else View.GONE
        }


        // Validacion de Pickers
        binding.btnAccept.setOnClickListener {

            // Validacion de Plataforma
            val selectedId = binding.rgPlatform.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Debe seleccionar una plataforma", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            // Validacion de Preferencias
            val isAnyPreferenceChecked =
                binding.cbProgramming.isChecked ||
                        binding.cbNetworks.isChecked ||
                        binding.cbHardware.isChecked ||
                        binding.cbSecurity.isChecked ||
                        binding.cbOther.isChecked

            if (!isAnyPreferenceChecked) {
                Toast.makeText(this, "Debe seleccionar al menos una preferencia", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }
}
