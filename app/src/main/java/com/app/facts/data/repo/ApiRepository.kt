package com.app.facts.data.repo

import com.app.facts.data.model.FactsModel

interface ApiRepository {

    suspend fun getAnimalFacts(): ResultState<FactsModel>
}