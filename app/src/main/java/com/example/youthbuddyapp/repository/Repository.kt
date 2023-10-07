package com.example.youthbuddyapp.repository

import android.util.Log
import com.example.youthbuddyapp.api.ApiInterface
import com.example.youthbuddyapp.models.BotInput
import com.example.youthbuddyapp.models.BotRecomandation
import com.example.youthbuddyapp.models.BotResponse
import retrofit2.HttpException
import retrofit2.Response

object Repository: DataSource {
    private lateinit var service: ApiInterface

    operator fun invoke(service: ApiInterface): Repository {
        synchronized(this){
            if(!this::service.isInitialized){
                this.service = service}
            return this
        }
    }
    override suspend fun askGoogleBot(input: BotInput): BotResponse<BotRecomandation> {
        return handleApi{ service.askBot(input)}
    }

    private suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): BotResponse<T> {
        return try {
            val response = execute()
            val body = response.body()
            Log.d("testt","${response.body()}")
            if (response.isSuccessful && body != null) {
                BotResponse.Success(body)
            } else {
                if (response.code() == 401) {
                    BotResponse.Unauthorized()
                } else {
                    val message: String? = null
                    BotResponse.Error(
                        code = response.code(), message = response.message(), parsedError = message
                    )
                }
            }
        } catch (e: HttpException) {
            BotResponse.Error(code = e.code(), message = e.message(), parsedError = null)
        } catch (e: Throwable) {
            BotResponse.Exception(message = e.message)
        }
    }
}