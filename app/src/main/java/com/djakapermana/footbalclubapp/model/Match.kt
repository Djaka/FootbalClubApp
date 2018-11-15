package com.djakapermana.footbalclubapp.model

import com.google.gson.annotations.SerializedName

data class Match(
        @SerializedName("idEvent")
        val idEvent: Int,

        @SerializedName("strEvent")
        val strEvent: String,

        @SerializedName("strHomeTeam")
        val strHomeTeam: String,

        @SerializedName("strAwayTeam")
        val strAwayTeam: String,

        @SerializedName("intHomeScore")
        val intHomeScore: Int,

        @SerializedName("intAwayScore")
        val intAwayScore: Int,

        @SerializedName("dateEvent")
        val strDate: String,

        @SerializedName("strTime")
        val strTime: String,

        @SerializedName("idHomeTeam")
        val idHomeTeam: Int,

        @SerializedName("idAwayTeam")
        val idAwayTeam: Int
)