package com.app.facts.factsList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import com.app.facts.presentation.MainActivity

@ExperimentalFoundationApi
@HiltAndroidTest
class FactsItemTest {

    @get : Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun validateIsMovieListVisible(){
        composeTestRule.apply{
            Thread.sleep(2000)
            onNodeWithTag("factsList").assertIsDisplayed()
        }
    }

}