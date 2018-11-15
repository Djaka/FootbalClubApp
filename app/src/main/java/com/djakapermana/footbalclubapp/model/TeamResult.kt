package com.djakapermana.footbalclubapp.model

import com.google.gson.annotations.SerializedName

data class TeamResult (
        @SerializedName("teams")
        val teams:List<Team>
)