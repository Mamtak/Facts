package com.app.facts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.facts.presentation.base.BaseRepository
import com.app.facts.presentation.base.BaseViewModel
import com.app.facts.utils.coroutines.CoroutineTestRule
import com.app.facts.utils.coroutines.DispatcherProviderTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

abstract class BaseViewModelRepositoryTest<Vm : BaseViewModel<Repo>, Repo : BaseRepository> {
    protected lateinit var viewModelUnderTest: Vm

    protected lateinit var repository: Repo

    protected val mockDataClassGenerator: MockDataClassGenerator = MockDataClassGenerator()

    @ExperimentalCoroutinesApi
    @get:Rule
    open val mainCoroutineRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val appDispatcher = DispatcherProviderTest()

    @ExperimentalCoroutinesApi
    @Before
    abstract fun setUp()
}
