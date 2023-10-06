package com.example.youthbuddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.youthbuddyapp.databinding.Info2Binding

class SecondInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: Info2Binding =
            Info2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, ThirdInfoActivity::class.java)
            startActivity(intent)
        }
    }
}
