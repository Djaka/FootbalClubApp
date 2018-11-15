package com.djakapermana.footbalclubapp.model

import com.google.gson.annotations.SerializedName

data class MatchDetail(
        @SerializedName("idEvent")
        val idEvent:Int,

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

        @SerializedName("strHomeGoalDetails")
        val strHomeGoalDetails: String,

        @SerializedName("strHomeRedCards")
        val strHomeRedCards: String,

        @SerializedName("strHomeYellowCards")
        val strHomeYellowCards: String,

        @SerializedName("strHomeLineupGoalkeeper")
        val strHomeLineupGoalkeeper: String,

        @SerializedName("strHomeLineupDefense")
        val strHomeLineupDefense: String,

        @SerializedName("strHomeLineupMidfield")
        val strHomeLineupMidfield: String,

        @SerializedName("strHomeLineupForward")
        val strHomeLineupForward: String,

        @SerializedName("strHomeLineupSubstitutes")
        val strHomeLineupSubstitutes: String,

        @SerializedName("strHomeFormation")
        val strHomeFormation: String,

        @SerializedName("strAwayRedCards")
        val strAwayRedCards: String,

        @SerializedName("strAwayYellowCards")
        val strAwayYellowCards: String,

        @SerializedName("strAwayGoalDetails")
        val strAwayGoalDetails: String,

        @SerializedName("strAwayLineupGoalkeeper")
        val strAwayLineupGoalkeeper: String,

        @SerializedName("strAwayLineupDefense")
        val strAwayLineupDefense: String,

        @SerializedName("strAwayLineupMidfield")
        val strAwayLineupMidfield: String,

        @SerializedName("strAwayLineupForward")
        val strAwayLineupForward: String,

        @SerializedName("strAwayLineupSubstitutes")
        val strAwayLineupSubstitutes: String,

        @SerializedName("strAwayFormation")
        val strAwayFormation: String,

        @SerializedName("intHomeShots")
        val intHomeShots: String,

        @SerializedName("intAwayShots")
        val intAwayShots: String,

        @SerializedName("idHomeTeam")
        val idHomeTeam: Int,

        @SerializedName("idAwayTeam")
        val idAwayTeam: Int,

        @SerializedName("dateEvent")
        val strDate: String,

        @SerializedName("strTime")
        val strTime: String
)