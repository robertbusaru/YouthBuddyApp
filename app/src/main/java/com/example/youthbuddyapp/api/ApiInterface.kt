package com.example.youthbuddyapp.api

import com.example.youthbuddyapp.models.BotInput
import com.example.youthbuddyapp.models.BotRecomandation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers(
        "Authorization: Bearer ya29.a0AfB_byAq5NsAyzFJwpfBHVenSdAuyEwUabcjyIZrHHGrKVmEw_gd5ed23pwyyNAbomLmNUODcltv8Cv2VdqaVaWiZfvUNHNCiwr7byqNAqVvu97JfPJPLaqoegNnn4dFu6zczz7TdA5FhL-dHOQpyp9JXQ4j6V0CBuX6kQvtcd6_aCgYKARASARMSFQGOcNnCFlZxkJbh-QqPqYCSgO2oWg0179",
        "x-goog-user-project: youthbuddyapp"
    )
    @POST("v2/projects/youthbuddyapp/agent/sessions/1234:detectIntent")
    suspend fun askBot(@Body query_input: BotInput): Response<BotRecomandation>
}