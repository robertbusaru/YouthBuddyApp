package com.example.youthbuddyapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youthbuddyapp.api.RetrofitInstance
import com.example.youthbuddyapp.models.BotInput
import com.example.youthbuddyapp.models.BotRecomandation
import com.example.youthbuddyapp.models.BotResponse
import com.example.youthbuddyapp.models.JobRecommandation
import com.example.youthbuddyapp.models.MyText
import com.example.youthbuddyapp.models.QueryInput
import com.example.youthbuddyapp.repository.Repository
import kotlinx.coroutines.launch

class BotViewModel: ViewModel() {
    val botResponse: MutableLiveData<BotRecomandation> = MutableLiveData()
    val repository = Repository(RetrofitInstance.api) //invoke

    fun sendData(botInput: BotInput){
        viewModelScope.launch {
            val response = repository.askGoogleBot(botInput)

            when (response) {
                is BotResponse.Success ->{ botResponse.value = response.data
                    Log.d("testamm","response.data --> ${response.data}")
                }
                is BotResponse.Unauthorized -> {botResponse.value=
                    BotRecomandation(JobRecommandation("full+time"))
                }
                is BotResponse.Exception -> {botResponse.value=
                    BotRecomandation(JobRecommandation("full+time"))}
                is BotResponse.Error -> {botResponse.value=
                    BotRecomandation(JobRecommandation("full+time"))}
                is BotResponse.OtherException->{botResponse.value=
                    BotRecomandation(JobRecommandation("full+time"))}
            }
        }
    }
}