package com.dean.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.dean.moviecatalogue.viewmodel.EspressoIdlingResource
import com.dean.moviecatalogue.views.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Rule

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovieAndTvShow() {
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        Espresso.onView(withText(R.string.tv_shows)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_shows))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        Espresso.onView(withText(R.string.movies)).perform(ViewActions.click())
    }

    @Test
    fun detailMovie() {
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Espresso.onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5,
                    ViewActions.click()
                ))

        Espresso.onView(withId(R.id.img_detail_hightlight))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_detail_poster))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_name))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_desc))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.pressBack()
    }

    @Test
    fun detailTvShow() {
        Espresso.onView(withText(R.string.tv_shows)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_shows))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Espresso.onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5,
                    ViewActions.click()
                ))

        Espresso.onView(withId(R.id.img_detail_hightlight))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.img_detail_poster))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_name))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_desc))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.pressBack()
    }
}