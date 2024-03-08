package com.app.facts

import com.app.facts.domain.model.FactsModel
import com.app.facts.core.common.DataError
import com.app.facts.core.common.ResultState
import com.app.facts.core.common.Success
import com.app.facts.core.utils.GENERIC_ERROR
import com.app.facts.core.utils.NO_INTERNET_CONNECTION

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