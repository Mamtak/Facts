package com.app.facts.ui

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.facts.data.FactsRepository
import com.app.facts.data.model.FactsModel
import com.app.facts.data.repo.DataError
import com.app.facts.data.repo.ResultState
import com.app.facts.ui.base.BaseViewModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


class MainActivityViewModel (
    factsRepository: FactsRepository
) : BaseViewModelRepository<FactsRepository>(factsRepository) {

    @VisibleForTesting(otherwise = PRIVATE)
    val factsResponsePrivate = MutableLiveData<ResultState<FactsModel>>()
    val factsResponse: LiveData<ResultState<FactsModel>> get() = factsResponsePrivate

    fun loadingFacts() {
                viewModelScope.launch(exceptionHandler) {

                    getRepository().getAnimalFactsData()
                        .collect { factResult ->
                            if (factResult.result != null) {
                                factsResponsePrivate.value = factResult
                            } else {
                                showMessageDialog(factResult as DataError<String>)
                            }

                        }

                }
    }
}
