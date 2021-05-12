package com.fave.assignment.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.paging.ExperimentalPagingApi
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.fave.assignment.R
import com.fave.assignment.data.constants.Constants
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailActivityTest {

    val duration:Long = 5000

    @ExperimentalPagingApi
    @Test
    fun whenActivityLoaded_withMovieId_shouldCheckViewVisible() {
        launch_detail_activity()
        Thread.sleep(duration)
        onView(withId(R.id.imgVPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenres)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDuration)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverviewHeader)).check(matches(isDisplayed()))
        onView(withId(R.id.tvoverview)).check(matches(isDisplayed()))
        onView(withId(R.id.btnBook)).check(matches(isDisplayed()))
    }

    @ExperimentalPagingApi
    @Test
    fun whenActivityLoaded_withMovieId_shouldCheckBookMovie() {
        launch_detail_activity()
        Thread.sleep(duration)
        onView(withId(R.id.btnBook)).perform(click())
    }

    @ExperimentalPagingApi
    fun launch_detail_activity(){
        val intent = Intent(getApplicationContext(), MovieDetailActivity::class.java)
        intent.putExtra(Constants.MOVIE_ID, 328111)
        intent.putExtra(Constants.MOVIE_TITLE, "The Secret Life of Pets")
       launchActivity<MovieDetailActivity>(intent)
    }

}
