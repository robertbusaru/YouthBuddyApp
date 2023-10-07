package com.example.youthbuddyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youthbuddyapp.databinding.SuccessfullyActivityBinding

class SuccessfullyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: SuccessfullyActivityBinding =
            SuccessfullyActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, SuccessfullyActivity::class.java)
            startActivity(intent)
        }

    }
}