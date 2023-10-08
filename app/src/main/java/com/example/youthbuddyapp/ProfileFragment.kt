package com.example.youthbuddyapp

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.youthbuddyapp.databinding.ProfileFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        Picasso.get().load(firebaseAuth.currentUser?.photoUrl).into(binding.imvCircular)
        binding.employeeName.text = firebaseAuth.currentUser?.displayName
        binding.employeeRole.text = firebaseAuth.currentUser?.email

        db.collection("users").get().addOnSuccessListener { documents ->
                for (item in documents) {
                    if(item.id==firebaseAuth.currentUser?.uid) {
                        val location = (item.data["location"] as String)
                        Log.d("userCheck", location)
                        binding.locationEt.editText?.setText(location)
                        binding.currentDomainEt.editText?.setText(item.data["employmentDomain"].toString())
                        binding.skillsEt.editText?.setText(item.data["skillsDevelop"].toString())
                    }
                }
            }


        return binding.root
    }

}