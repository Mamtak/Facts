package com.app.facts.core.common

import com.app.facts.core.utils.GENERIC_ERROR

sealed class ResultState<T>(
    val result: T? = null,
    val errorMessage: String = GENERIC_ERROR,
) {


    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$result]"
            is DataError -> "Error[exception=$errorMessage]"
        }
    }

}

class Success<T>(data: T) : ResultState<T>(data)
class DataError<T>(errorMessage: String) : ResultState<T>(null, errorMessage)