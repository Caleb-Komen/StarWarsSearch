package com.arch.starwarssearch.ui.characters

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arch.starwarssearch.DataFactory
import com.arch.starwarssearch.R
import com.arch.starwarssearch.util.launchFragmentInHiltContainer
import com.arch.starwarssearch.local.StarWarsDatabase
import com.arch.starwarssearch.local.dao.CharacterDao
import com.arch.starwarssearch.local.mapper.toEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CharactersFragmentTest {
    lateinit var database: StarWarsDatabase

    lateinit var characterDao: CharacterDao

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val char1 = DataFactory.char1
    private val char2 = DataFactory.char2

    @Before
    fun setup(){
        StarWarsDatabase.instance = Room
            .inMemoryDatabaseBuilder(context, StarWarsDatabase::class.java)
            .build()
        database = StarWarsDatabase.getInstance(context)
        characterDao = database.characterDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun characters_displayOnUi() = runTest{
        characterDao.insertCharacter(char1.toEntity())
        characterDao.insertCharacter(char2.toEntity())

        launchFragmentInHiltContainer<CharactersFragment>()

        // confirm recyclerview is visible and some characters are displayed
        onView(withId(R.id.rv_characters)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.layout_no_characters)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withText("Luke Skywalker")).check(matches(isDisplayed()))
        onView(withText("C-3PO")).check(matches(isDisplayed()))
    }

}