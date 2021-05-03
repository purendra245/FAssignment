package com.fave.assignment.presentation.movie


import androidx.paging.ExperimentalPagingApi
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.fave.assignment.R
import com.fave.assignment.presentation.adapter.MovieListAdapter
import com.fave.assignment.presentation.adapter.MovieViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainMovieActivityTest {

    @ExperimentalPagingApi
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainMovieActivity::class.java)

    @Test
    fun whenActivityLoaded_shouldCheckViewVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.swipeRefreshLayout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun whenListLoaded_clickOnItem() {
        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.rvMovie)).perform(scrollToPosition<MovieViewHolder>(5))
        onView(ViewMatchers.withId(R.id.rvMovie)).perform(actionOnItemAtPosition<MovieViewHolder>(5, click()))
    }

}
