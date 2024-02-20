package com.app.facts

import com.app.facts.data.model.FactsModel
import com.app.facts.data.repo.DataError
import com.app.facts.data.repo.ResultState
import com.app.facts.data.repo.Success
import com.app.facts.utils.GENERIC_ERROR
import com.app.facts.utils.NO_INTERNET_CONNECTION

class MockDataClassGenerator {

    fun getSuccessFactsResponse(): ResultState<FactsModel> {
        val data = arrayListOf<String>("First", "Second", "Third")
        return Success(FactsModel(data, true))
    }

    fun getErrorResponse():ResultState<FactsModel> {

        return DataError(GENERIC_ERROR)
    }

    fun getNoNetworkError():ResultState<Any> {

        return DataError(NO_INTERNET_CONNECTION)
    }
}