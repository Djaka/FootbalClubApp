package com.djakapermana.footbalclubapp.model

import com.google.gson.annotations.SerializedName

data class NextMatchResult (
        @SerializedName("events")
        val events:List<Match>
)