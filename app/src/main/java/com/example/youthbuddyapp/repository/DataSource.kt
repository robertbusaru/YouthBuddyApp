package com.example.youthbuddyapp.repository

import com.example.youthbuddyapp.models.BotInput
import com.example.youthbuddyapp.models.BotRecomandation
import com.example.youthbuddyapp.models.BotResponse

interface DataSource {
    suspend fun askGoogleBot(input: BotInput): BotResponse<BotRecomandation>
}