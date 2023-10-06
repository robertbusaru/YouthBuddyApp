package com.example.youthbuddyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.youthbuddyapp.databinding.PeopleFragmentBinding

class PeopleFragment : Fragment() {

    private lateinit var binding: PeopleFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PeopleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}