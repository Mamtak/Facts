package com.app.facts.factsList

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.app.facts.presentation.components.FactsItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FactsItemShould {

    @get:Rule
    val composerule = createComposeRule()
    private lateinit var factsModelList : List<String>

    @Before
    fun setUp() {
        factsModelList = listOf("Test data", "Test data 2", "Test Data 3")
        composerule.setContent {
            FactsItem(factsModelList.get(0))
        }
    }

    @Test
    fun beDispalyed(){
        composerule.onNodeWithTag("factsItem").assertIsDisplayed()
    }

    @Test
    fun containCorrectTitle(){
        composerule.onNodeWithText("Test data")
    }
}