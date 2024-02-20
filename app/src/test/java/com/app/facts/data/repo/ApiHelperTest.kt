package com.app.facts.data.repo

import android.content.Context
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Before
import org.junit.Test

class ApiHelperTest {
    lateinit var sut: ApiHelper;
    val mContextMock = mockk<Context>()

    @Before
    fun setUp() {
        sut = spyk(ApiHelper(mContextMock))
    }

    @Test
    fun test(){

    }

}