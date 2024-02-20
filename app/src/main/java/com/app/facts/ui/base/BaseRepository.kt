package com.app.facts.ui.base

import com.app.facts.data.repo.ApiRepository
import com.app.facts.utils.coroutines.DispatcherProvider


open class BaseRepository(
    private val appDispatcher: DispatcherProvider,
    private val apiRepository: ApiRepository
) {

    fun getAppDispatcher(): DispatcherProvider {
        return appDispatcher
    }

    fun getApiRepository(): ApiRepository {
        return apiRepository
    }

}