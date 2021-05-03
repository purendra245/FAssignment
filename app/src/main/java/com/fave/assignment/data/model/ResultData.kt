package com.fave.assignment.data.model


sealed class ResultData<out T: Any> {
    data class Loading(val nothing: Nothing? = null): ResultData<Nothing>()
    data class Failed(val message: String? = null): ResultData<Nothing>()
    data class Error(val error: Exception): ResultData<Nothing>()
    data class Success<out T: Any>(val data: T) : ResultData<T>()


}