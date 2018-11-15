package com.djakapermana.footbalclubapp.model

import com.google.gson.annotations.SerializedName

data class MatchDetailResult(
        @SerializedName("events")
        val events: List<MatchDetail>
)