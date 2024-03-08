package com.app.facts.data

import com.app.facts.core.common.DataError
import com.app.facts.core.common.ResultState
import com.app.facts.core.common.Success
import com.app.facts.domain.repo.ApiRepository
import com.app.facts.presentation.base.BaseRepository
import com.app.facts.core.utils.coroutines.DispatcherProvider
import com.app.facts.domain.model.FactsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FactsRepository @Inject constructor(
    appDispatcher: DispatcherProvider,
    apiRepository: ApiRepository
) : BaseRepository(appDispatcher,apiRepository) {


    suspend fun getAnimalFactsData(): Flow<ResultState<FactsModel>> {

        val factResultData = getApiRepository().getAnimalFacts()
        factResultData.result?.let {
            when(it){
                is FactsModel -> Success(factResultData.result)
                else -> {
                    return flow {
                        emit(DataError(factResultData.errorMessage))
                    }
                }
            }
//
            if (it.success) {
                val factsResponse = factResultData.result

            } else {
                return flow {
                    emit(DataError(factResultData.errorMessage))
                }
            }
        }

        return flow {
            emit(factResultData)
        }

    }

}