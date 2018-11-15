package com.djakapermana.footbalclubapp.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge")
        val strTeamBadge: String
)