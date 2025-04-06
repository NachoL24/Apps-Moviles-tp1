package com.example.primerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)

        val cbOther = findViewById<CheckBox>(R.id.cbOther)
        val etOtherSpecify = findViewById<EditText>(R.id.etOtherSpecify)
        val btnAccept = findViewById<Button>(R.id.btnAccept)

        cbOther.setOnCheckedChangeListener { _, isChecked ->
            etOtherSpecify.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        btnAccept.setOnClickListener {
            if (cbOther.isChecked && etOtherSpecify.text.isNullOrEmpty()) {
                etOtherSpecify.error = "Por favor especifica al menos una preferencia"
            } else {
                Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}