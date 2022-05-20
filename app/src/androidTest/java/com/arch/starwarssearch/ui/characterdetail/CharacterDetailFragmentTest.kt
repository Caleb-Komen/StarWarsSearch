package com.arch.starwarssearch.ui.characterdetail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arch.starwarssearch.*
import com.arch.starwarssearch.mapper.toPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CharacterDetailFragmentTest {
    private val char = DataFactory.char1

    @Test
    fun characterDetails_displayOnUi() {
        val args = CharacterDetailFragmentArgs(char.character.toPresentation()).toBundle()
        launchFragmentInHiltContainer<CharacterDetailFragment>(args, R.style.Theme_StarWarsSearch)

        onView(withId(R.id.tv_character_name)).check(matches(withText("Luke Skywalker")))
        onView(withId(R.id.tv_character_height)).check(matches(withText("172")))
        onView(withId(R.id.tv_character_birth_year)).check(matches(withText("19 BBY")))
    }
}