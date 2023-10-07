package com.example.youthbuddyapp.api

import com.example.youthbuddyapp.models.BotInput
import com.example.youthbuddyapp.models.BotRecomandation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers(
        "Authorization: Bearer ya29.a0AfB_byBJ9FCtGZMb3Xw8tW6orHm7iXnw4_DBMSymFweWqULZgjj9ifnBNpqWnMIWHzyw12IoKYmuV96NNyrNK_5MQqbxsEmovcZcDqNPUW85a7dFmnKtrSEM6D3fKXMfSIZuNTYQ_9NJ856nkIjKrIb3ufP9jX33YeYyK3TUsyHbaCgYKAecSARMSFQGOcNnCwaZ5KH0O-wIjI3nlMZQA2Q0179",
        "x-goog-user-project: youthbuddyapp"
    )
    @POST("v2/projects/youthbuddyapp/agent/sessions/1234:detectIntent")
    suspend fun askBot(@Body query_input: BotInput): Response<BotRecomandation>
}