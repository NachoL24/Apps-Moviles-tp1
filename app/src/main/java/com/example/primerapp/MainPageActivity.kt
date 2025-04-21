package com.example.primerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.primerapp.databinding.MainpageBinding

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: MainpageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val themeMode = sharedPreferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(themeMode)

        super.onCreate(savedInstanceState)
        binding = MainpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = "Juan Torres"
        val welcome = getString(R.string.welcome_message, userName)
        binding.tvWelcomeTitle.text = welcome

        binding.cbOther.setOnCheckedChangeListener { _, isChecked ->
            binding.etOtherSpecify.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        binding.rbAndroid.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.rbApple.isChecked = false
        }
        binding.rbApple.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.rbAndroid.isChecked = false
        }

        binding.btnAccept.setOnClickListener {
            val platformSelected = binding.rbAndroid.isChecked || binding.rbApple.isChecked
            if (!platformSelected) {
                Toast.makeText(this, "Debe seleccionar una plataforma.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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

            Toast.makeText(this, "Información guardada correctamente.", Toast.LENGTH_SHORT).show()
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
            delegate.applyDayNight()
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
    }

    private fun saveThemePreference(mode: Int) {
        val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("theme_mode", mode)
        editor.apply()
    }
}
