package com.example.youthbuddyapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youthbuddyapp.databinding.PeopleFragmentBinding
import com.example.youthbuddyapp.models.People
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking

class PeopleFragment : Fragment() {

    private lateinit var binding: PeopleFragmentBinding
    private lateinit var adapter: PeopleAdapter
    private var people:ArrayList<People> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PeopleFragmentBinding.inflate(inflater, container, false)
        adapter = PeopleAdapter(requireContext(),people)
        Firebase.firestore.collection("users").get().addOnSuccessListener { documents->
                for (document in documents){
                    Log.d("testamm","document ->> ${document.data["name"]}")
                    var p = People(document.data["profilePicture"] as String,
                        document.data["name"] as String,
                        document.data["email"] as String)
                    people.add(p)
                }
                Log.d("testamm","document ->> ${people}")
                adapter.addPeople(people)
            }


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        return binding.root
    }

}