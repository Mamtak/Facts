package com.app.facts.data

import com.app.facts.data.model.FactsModel
import com.app.facts.data.repo.ApiRepository
import com.app.facts.data.repo.DataError
import com.app.facts.data.repo.ResultState
import com.app.facts.ui.base.BaseRepository
import com.app.facts.utils.coroutines.DispatcherProvider
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