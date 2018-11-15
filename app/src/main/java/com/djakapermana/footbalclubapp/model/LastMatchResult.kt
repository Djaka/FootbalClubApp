package com.djakapermana.footbalclubapp.model

import com.google.gson.annotations.SerializedName

data class LastMatchResult (
        @SerializedName("events")
        val events:List<Match>
)