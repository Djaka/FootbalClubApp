package com.djakapermana.footbalclubapp

import com.djakapermana.footbalclubapp.helper.GeneralFunctions
import com.djakapermana.footbalclubapp.interfaces.IMatch
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class MatchUnitTest {

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadDataLastMatch() {
        val macth: IMatch = GeneralFunctions.retrofit.create(IMatch::class.java)
        val call = macth.getLastMatch()
        assertEquals("Man City",call.execute().body()?.events?.get(1)?.strHomeTeam)
    }

    @Test
    fun loadDataNextMatch() {
        val macth: IMatch = GeneralFunctions.retrofit.create(IMatch::class.java)
        val call = macth.getNextMatch()
        assertEquals("Watford",call.execute().body()?.events?.get(1)?.strHomeTeam)
    }

    @Test
    fun loadDataMatchDetail() {
        val macth: IMatch = GeneralFunctions.retrofit.create(IMatch::class.java)
        val call = macth.getMatchDetail("576590")
        assertEquals("Tottenham",call.execute().body()?.events?.get(0)?.strHomeTeam)
    }

    @Test
    fun loadDataImageDetail() {
        val macth: IMatch = GeneralFunctions.retrofit.create(IMatch::class.java)
        val call = macth.getImageTeam("133616")
        assertEquals("https://www.thesportsdb.com/images/media/team/badge/rxxqtp1448813512.png",call.execute().body()?.teams?.get(0)?.strTeamBadge)
    }
}