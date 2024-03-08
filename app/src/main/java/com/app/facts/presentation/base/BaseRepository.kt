package com.app.facts.presentation.base

import com.app.facts.domain.repo.ApiRepository
import com.app.facts.core.utils.coroutines.DispatcherProvider


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