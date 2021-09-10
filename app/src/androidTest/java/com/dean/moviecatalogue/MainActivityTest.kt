package com.dean.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Test
import org.junit.Rule
import kotlin.concurrent.thread

class MainActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movies)) .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)) .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun detailMovie() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.view_pager)).perform(
            swipeLeft())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun detailTvShows() {
        onView(withId(R.id.view_pager)).perform(
            swipeLeft(), click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(isDisplayed()))
    }

}