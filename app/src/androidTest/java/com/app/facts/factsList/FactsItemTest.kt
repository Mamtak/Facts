package com.app.facts.factsList

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.app.facts.BaseInstrumentTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.app.facts.presentation.MainActivity
import com.app.facts.presentation.components.FactsItem

@ExperimentalFoundationApi
@HiltAndroidTest
class FactsItemTest :  BaseInstrumentTest(){

    @get : Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)
    private lateinit var activity: ComponentActivity
    private lateinit var fact: String

    @Before
    override fun setUp() {
        activity = composeTestRule.activity
        fact = "Many foot disorders in dogs are simply an issue of too-long toenails."

        composeTestRule.setContent {
            FactsItem(fact)
        }
    }


    @Test
    fun factListItemDisplayed(){
        composeTestRule.onNodeWithText(fact).assertIsDisplayed()
    }

}