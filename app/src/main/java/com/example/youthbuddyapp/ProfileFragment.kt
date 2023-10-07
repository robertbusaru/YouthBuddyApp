package com.example.youthbuddyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.youthbuddyapp.databinding.ProfileFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        Picasso.get().load(firebaseAuth.currentUser?.photoUrl).into(binding.imvCircular)
        binding.employeeName.text = firebaseAuth.currentUser?.displayName
        binding.employeeRole.text = firebaseAuth.currentUser?.email

        return binding.root
    }

}