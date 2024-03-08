package com.app.facts.domain.repo

import com.app.facts.core.common.ResultState
import com.app.facts.domain.model.FactsModel

interface ApiRepository {

    suspend fun getAnimalFacts(): ResultState<FactsModel>
}