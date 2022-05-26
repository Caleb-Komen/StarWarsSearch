package com.arch.starwarssearch.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arch.starwarssearch.R
import com.arch.starwarssearch.ui.characters.CharactersListAdapter.CharactersViewHolder
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class StarWarsActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(StarWarsActivity::class.java)

    @Test
    fun searchStarWarsCharacter(){
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.et_search_field)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.rv_characters), withContentDescription(R.string.characters_from_network))).check(matches(isDisplayed()))
        onView(withId(R.id.et_search_field)).perform(click(), typeText("Luke"), closeSoftKeyboard())

        onView(allOf(withId(R.id.rv_characters), withContentDescription(R.string.characters_from_network)))
            .perform(RecyclerViewActions.scrollToPosition<CharactersViewHolder>(0), click())
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()))
    }
}