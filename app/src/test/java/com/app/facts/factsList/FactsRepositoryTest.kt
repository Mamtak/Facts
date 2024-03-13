package com.app.facts.factsList

import com.app.facts.MockDataClassGenerator
import com.app.facts.core.common.ResultState
import com.app.facts.data.FactsRepository
import com.app.facts.domain.model.FactsModel
import com.app.facts.domain.repo.ApiRepository
import com.app.facts.core.utils.NO_INTERNET_CONNECTION
import com.app.facts.utils.coroutines.DispatcherProviderTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class FactsRepositoryTest{
    lateinit var apiRepository: ApiRepository

    lateinit var repoUnderTest: FactsRepository

    protected val mockDataClassGenerator: MockDataClassGenerator = MockDataClassGenerator()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setTup() {
        apiRepository = mockk(relaxUnitFun = true)
        val appDispatcher = DispatcherProviderTest()
        repoUnderTest = FactsRepository(appDispatcher,apiRepository)

    }

    @Test
    fun factsAPISuccessResponseTest() {

        //Given
        coEvery {
            apiRepository.getAnimalFacts()
        } returns mockDataClassGenerator.getSuccessFactsResponse()


        //when
        var result: ResultState<FactsModel>? = null

        runBlocking  {
            result =  repoUnderTest.getAnimalFactsData().first()
        }

        //then
        org.junit.Assert.assertEquals(true, result!!.result!!.facts)
        coVerify  (exactly = 1){
            apiRepository.getAnimalFacts()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun factsAPIErrorResponseResultTest() {

        //Given
        coEvery {
            apiRepository.getAnimalFacts(
            )
        } returns mockDataClassGenerator.getErrorResponse()

        //when
        var result: ResultState<FactsModel>? = null

        runBlockingTest  {
            result =  repoUnderTest.getAnimalFactsData().first()
        }

        //then
        org.junit.Assert.assertEquals("Something went wrong", result!!.errorMessage)


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun factsAPINoNetworkConnectionEmptyListTest() {

        //Given
        coEvery {
            apiRepository.getAnimalFacts()
        } returns mockDataClassGenerator.getNoNetworkError() as ResultState<FactsModel>


        //when
        var result: ResultState<FactsModel>? = null
        runBlocking  {
            result =  repoUnderTest.getAnimalFactsData().first()
        }
        //then
        assertEquals(NO_INTERNET_CONNECTION, result!!.errorMessage)

    }

}