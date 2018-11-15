package com.djakapermana.footbalclubapp.model

data class FavoriteMatch(
        val idEvent: Int,
        val strEvent: String?,
        val strHomeTeam: String?,
        val strAwayTeam: String,
        val intHomeScore: Int,
        val intAwayScore: Int,
        val strHomeGoalDetails: String?,
        val strHomeRedCards: String?,
        val strHomeYellowCards: String?,
        val strHomeLineupGoalkeeper: String?,
        val strHomeLineupDefense: String?,
        val strHomeLineupMidfield: String?,
        val strHomeLineupForward: String?,
        val strHomeLineupSubstitutes: String?,
        val strHomeFormation: String?,
        val strAwayRedCards: String?,
        val strAwayYellowCards: String?,
        val strAwayGoalDetails: String?,
        val strAwayLineupGoalkeeper: String?,
        val strAwayLineupDefense: String?,
        val strAwayLineupMidfield: String?,
        val strAwayLineupForward: String?,
        val strAwayLineupSubstitutes: String?,
        val strAwayFormation: String?,
        val intHomeShots: String?,
        val intAwayShots: String?,
        val idHomeTeam: Int,
        val idAwayTeam: Int,
        val strDate: String?,
        val strTime: String
){
    companion object {
        const val TABLE_FAVORITE = "TABLE_FAVORITE"
        const val idEvent: String = "ID_EVENT"
        const val strEvent: String = "EVENT"
        const val strHomeTeam: String = "HOME_TEAM"
        const val strAwayTeam: String = "AWAY_TEAM"
        const val intHomeScore: String = "HOME_SCORE"
        const val intAwayScore: String = "AWAY_SCORE"
        const val strHomeGoalDetails: String = "HOME_GOAL_DETAIL"
        const val strHomeRedCards: String = "HOME_RED_CARD"
        const val strHomeYellowCards: String = "HOME_YELLOW_CARD"
        const val strHomeLineupGoalkeeper: String = "HOME_LINE_UP_GK"
        const val strHomeLineupDefense: String = "HOME_LINE_UP_DEF"
        const val strHomeLineupMidfield: String = "HOME_LINE_UP_MID"
        const val strHomeLineupForward: String = "HOME_LINE_UP_FWD"
        const val strHomeLineupSubstitutes: String = "HOME_LINE_UP_SUB"
        const val strHomeFormation: String = "HOME_FORMATION"
        const val strAwayRedCards: String = "AWAY_RED_CARD"
        const val strAwayYellowCards: String = "AWAY_YELLOW_CARD"
        const val strAwayGoalDetails: String = "AWAY_GOAL_DETAIL"
        const val strAwayLineupGoalkeeper: String = "AWAY_LINE_UP_GK"
        const val strAwayLineupDefense: String = "AWAY_LINE_UP_DEF"
        const val strAwayLineupMidfield: String = "AWAY_LINE_UP_MID"
        const val strAwayLineupForward: String = "AWAY_LINE_UP_FWD"
        const val strAwayLineupSubstitutes: String = "AWAY_LINE_UP_SUB"
        const val strAwayFormation: String = "AWAY_FORMATION"
        const val intHomeShots: String = "HOME_SHOT"
        const val intAwayShots: String = "AWAY_SHOT"
        const val idHomeTeam: String = "ID_HOME_TEAM"
        const val idAwayTeam: String = "ID_AWAY_TEAM"
        const val strDate: String = "DATE"
        const val strTime: String = "TIME"
    }
}