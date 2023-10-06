package com.example.youthbuddyapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.youthbuddyapp.databinding.MainMenuActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMenuActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var binding: MainMenuActivityBinding
    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainMenuActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentMainMenu) as NavHostFragment
        navController = navHostFragment.navController
        navView = findViewById(R.id.bottomNavigationView)

        binding.bottomNavigationView.setOnItemReselectedListener { menuItem ->
            Toast.makeText(
                this, "$menuItem is already selected",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.bottomNavigationView.setupWithNavController(navController)

    }

}