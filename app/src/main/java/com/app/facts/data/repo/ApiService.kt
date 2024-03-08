package com.app.facts.data.repo

import com.app.facts.domain.model.FactsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("facts?number=10")
    suspend fun getAnimalFactsData(): Response<FactsModel>
}