package com.app.facts.presentation

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.facts.core.common.DataError
import com.app.facts.core.common.ResultState
import com.app.facts.data.FactsRepository
import com.app.facts.domain.model.FactsModel
import com.app.facts.presentation.base.BaseViewModelRepository
import kotlinx.coroutines.launch


class MainActivityViewModel (
    factsRepository: FactsRepository
) : BaseViewModelRepository<FactsRepository>(factsRepository) {

    @VisibleForTesting(otherwise = PRIVATE)
    private val _factsResponsePrivate = MutableLiveData<ResultState<FactsModel>>()
    val factsResponse: MutableLiveData<ResultState<FactsModel>> get() = _factsResponsePrivate

    init {
        loadingFacts()
    }

    fun loadingFacts() {
                viewModelScope.launch(exceptionHandler) {

                    getRepository().getAnimalFactsData()
                        .collect { factResult ->
                            if (factResult.result != null) {
                                _factsResponsePrivate.value = factResult
                            } else {
                                showMessageDialog(factResult as DataError<String>)
                            }

                        }

                }
    }
}
