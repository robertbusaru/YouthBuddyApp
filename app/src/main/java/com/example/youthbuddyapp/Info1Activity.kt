package com.example.youthbuddyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youthbuddyapp.databinding.Info1Binding

class Info1Activity : AppCompatActivity() {

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