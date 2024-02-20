package com.app.facts.data.repo

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.app.facts.data.model.FactsModel
import com.app.facts.utils.GENERIC_ERROR
import com.app.facts.utils.NO_INTERNET_CONNECTION
import com.app.facts.utils.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

 class ApiHelper @Inject constructor(
    @ApplicationContext val context: Context
) : ApiRepository {

    @Inject
    lateinit var serviceProvider: ServiceProvider

    override suspend fun getAnimalFacts(): ResultState<FactsModel> {
        val service = serviceProvider.apiService

        return when (val response = processCall { service.getAnimalFactsData() }) {
            is FactsModel -> Success(response)
            else -> DataError(response as String)
        }
    }

    @VisibleForTesting(otherwise = PRIVATE)
    public inline fun processCall(responseCall: () -> Response<*>): Any? {
        if (!NetworkUtils.isNetworkAvailable(context)) {

            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                getResponseCodeString(responseCode)
            }
        } catch (e: IOException) {
            GENERIC_ERROR
        }
    }

    public fun getResponseCodeString(responseCode: Int): String {
       // can add error according to different response code
        return GENERIC_ERROR
    }

 }