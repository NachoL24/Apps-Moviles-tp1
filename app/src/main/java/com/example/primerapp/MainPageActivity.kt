package com.example.primerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primerapp.databinding.MainpageBinding

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: MainpageBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = MainpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}