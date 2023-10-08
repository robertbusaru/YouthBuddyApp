package com.example.youthbuddyapp.api

import com.example.youthbuddyapp.models.BotInput
import com.example.youthbuddyapp.models.BotRecomandation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers(
        "Authorization: Bearer ya29.a0AfB_byC9lbSOylT3V_rK_QHypyQIN5Kzml4WEb5KxcGkDK9Mug5bDg9FoLnKRlbVpYFpu9RnzIIHVJzwsmvkHWYxjMpkRt1_Rg0QZauUJihm2ZkaYNuf0pG6r1YQnXlxShbBxxTU1D8FIycOMaXfKmLqeSCPkm-VmA6Wd8VKHjbzaCgYKAWMSARMSFQGOcNnCiScm6Ou7tB7dZ1D7-5Y68w0179",
        "x-goog-user-project: youthbuddyapp"
    )
    @POST("v2/projects/youthbuddyapp/agent/sessions/1234:detectIntent")
    suspend fun askBot(@Body query_input: BotInput): Response<BotRecomandation>
}