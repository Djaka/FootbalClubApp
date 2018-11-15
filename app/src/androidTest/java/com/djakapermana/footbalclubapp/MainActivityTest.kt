package com.djakapermana.footbalclubapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.djakapermana.footbalclubapp.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest{

    @Rule
    @JvmField
    val mainActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityBehaviour() {

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))

        onView(withId(R.id.tab_schedule)).check(matches(isDisplayed()))

        onView(withId(R.id.view_pager_schedule)).check(matches(isDisplayed()))

        onView(withText("PREV MATCH")).perform(click())

        Thread.sleep(3000)
        onView(withId(rec_last_match)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(rec_last_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
        Thread.sleep(3000)
        onView(withId(rec_last_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))
        Thread.sleep(3000)
        onView(withId(toolbar)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("This Event Added to Favorite")).check(matches(isDisplayed()))
        pressBack()
        Thread.sleep(3000)
        onView(withText("NEXT MATCH")).perform(click())
        Thread.sleep(3000)
        onView(withId(rec_next_match)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(rec_next_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        Thread.sleep(3000)
        onView(withId(rec_next_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        Thread.sleep(3000)
        pressBack()
        Thread.sleep(3000)
        onView(withText("FAVORITES")).perform(click())
        Thread.sleep(3000)
        onView(withId(rec_favorite_match)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(rec_favorite_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)
    }
}