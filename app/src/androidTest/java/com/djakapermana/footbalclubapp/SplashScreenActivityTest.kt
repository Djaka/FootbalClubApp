package com.djakapermana.footbalclubapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashScreenActivityTest{

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SplashScreenActivity::class.java)

    @Test
    fun testActivity() {
        onView(withId(R.id.img_splash)).check(matches(isDisplayed()))
    }
}