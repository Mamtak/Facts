package com.app.facts.data.repo

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

const val baseUrl="https://dog-api.kinduff.com/api/"
const val timeoutConnect = 30
const val timeoutRead = 30

@Singleton
class ServiceProvider @Inject constructor(){

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var retrofit: Retrofit

}