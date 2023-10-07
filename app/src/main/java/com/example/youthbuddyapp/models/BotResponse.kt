package com.example.youthbuddyapp.models

sealed class BotResponse<T: Any>{
    class Success<T: Any>(val data: T): BotResponse<T>()
    class Error<T : Any>(
        val code: Int?, val message: String?, val parsedError: String?
    ) : BotResponse<T>()

    class Unauthorized<T : Any> : BotResponse<T>()
    class Exception<T : Any>(
        val message: String?
    ) : BotResponse<T>()
    class OtherException<T:Any>(): BotResponse<T>()
}
