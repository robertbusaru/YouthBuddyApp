package com.example.youthbuddyapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youthbuddyapp.api.RetrofitInstance
import com.example.youthbuddyapp.models.BotInput
import com.example.youthbuddyapp.models.BotRecomandation
import com.example.youthbuddyapp.models.MyText
import com.example.youthbuddyapp.models.QueryInput
import com.example.youthbuddyapp.repository.Repository
import kotlinx.coroutines.launch

class BotViewModel: ViewModel() {
    val botResponse: MutableLiveData<BotRecomandation> = MutableLiveData()
    val repository = Repository(RetrofitInstance.api) //invoke

    fun sendData(botInput: BotInput){
        var testInput = BotInput(QueryInput(MyText("Sunt studend","ro-RO")))
        viewModelScope.launch {
            Log.d("testt","$testInput")
            val response = repository.askGoogleBot(testInput)
        }
    }
}