package com.example.youthbuddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.youthbuddyapp.databinding.Info3Binding

class ThirdInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: Info3Binding =
            Info3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, FourthInfoActivity::class.java)
            startActivity(intent)
        }
    }
}
