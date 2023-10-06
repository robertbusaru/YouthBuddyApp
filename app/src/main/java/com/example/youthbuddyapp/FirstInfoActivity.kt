package com.example.youthbuddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.youthbuddyapp.databinding.Info1Binding

class FirstInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: Info1Binding =
            Info1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, SecondInfoActivity::class.java)
            startActivity(intent)
        }
    }
}
