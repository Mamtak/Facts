package com.app.facts.utils.coroutines

import com.app.facts.core.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class DispatcherProviderTest : DispatcherProvider {

    val testDispatcher = TestCoroutineDispatcher()

    override fun default(): CoroutineDispatcher {
        return testDispatcher
    }

    override fun io(): CoroutineDispatcher {

        return testDispatcher
    }

    override fun main(): CoroutineDispatcher {
        return testDispatcher
    }
}