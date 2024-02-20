package com.app.facts.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.app.facts.BaseInstrumentTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.app.facts.R
import com.app.facts.data.model.FactsModel
import com.app.facts.ui.adapter.FactsListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@HiltAndroidTest
class MainActivityTest :  BaseInstrumentTest(){

    @get:Rule
    var hiltRule =HiltAndroidRule(this)
    lateinit var activity: AppCompatActivity

    @get:Rule(order = 1) // 2
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java) // 1

    @Before
    override fun setUp() {
        hiltRule.inject()
    }

    @Module
    @InstallIn(SingletonComponent::class) // 1
    object TestAppModule {

        @Provides
        fun provideFactsRepository(): FactsModel { // 2
            val arrayList  = arrayListOf<String>("abc1", "abc2", "abc3")
            return FactsModel(arrayList, true)
            }
        }


    @Test
    fun whenMainActivityLaunched(){
        TestAppModule.provideFactsRepository()
        scrollAtAndCheckTestVisible(0, "abc1")
        scrollAtAndCheckTestVisible(1, "abc2")
        scrollAtAndCheckTestVisible(2, "abc3")
    }

    fun scrollAtAndCheckTestVisible(position: Int, text: String) {
        onView(ViewMatchers.withId(R.id.factsRecyclerView))
            .perform(RecyclerViewActions
                .scrollToPosition<FactsListAdapter.FactsViewHolder>(position))
        onView(withText(text)).check(matches(isDisplayed()))
    }
}