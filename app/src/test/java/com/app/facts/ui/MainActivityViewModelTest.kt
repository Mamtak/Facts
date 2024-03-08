package com.app.facts.ui

import com.app.facts.BaseViewModelRepositoryTest
import com.app.facts.data.FactsRepository
import com.app.facts.domain.repo.ApiRepository
import com.app.facts.presentation.MainActivityViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Test

class MainActivityViewModelTest : BaseViewModelRepositoryTest<MainActivityViewModel, FactsRepository>() {
    lateinit var apiRepository: ApiRepository

    @ExperimentalCoroutinesApi
    override fun setUp() {
        apiRepository = mockk(relaxUnitFun = true)
        repository = mockk()
        viewModelUnderTest =  spyk(MainActivityViewModel(repository))
    }

    @Test
    fun `getAnimalFacts, return success`() {
        //Given
        coEvery {
            repository.getAnimalFactsData()
        } returns flow { emit(mockDataClassGenerator.getSuccessFactsResponse()) }


        //when
        viewModelUnderTest.factsResponsePrivate.observeForever {}

        //then
        val loginResponse = viewModelUnderTest.factsResponsePrivate.value!!.result

        val expectedResponse = mockDataClassGenerator.getSuccessFactsResponse().result


        assertEquals(expectedResponse, loginResponse)

        verify {
            coEvery {
                repository.getAnimalFactsData()
            }
        }

    }

}