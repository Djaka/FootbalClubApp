package com.djakapermana.footbalclubapp.interfaces

import com.djakapermana.footbalclubapp.model.LastMatchResult
import com.djakapermana.footbalclubapp.model.MatchDetailResult
import com.djakapermana.footbalclubapp.model.NextMatchResult
import com.djakapermana.footbalclubapp.model.TeamResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMatch {
    @GET("/api/v1/json/1/eventsnextleague.php?id=4328")
    fun getNextMatch(): Call<NextMatchResult>

    @GET("/api/v1/json/1/eventspastleague.php?id=4328")
    fun getLastMatch(): Call<LastMatchResult>

    @GET("api/v1/json/1/lookupevent.php")
    fun getMatchDetail(@Query("id") string: String): Call<MatchDetailResult>

    @GET("api/v1/json/1/lookupteam.php")
    fun getImageTeam(@Query("id") string: String): Call<TeamResult>
}