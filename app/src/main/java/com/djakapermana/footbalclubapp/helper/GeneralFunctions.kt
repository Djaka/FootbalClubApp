package com.djakapermana.footbalclubapp.helper

import com.djakapermana.footbalclubapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object GeneralFunctions {
    val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun timeFormater(str: String): String {
        return str.substring(0, 5)
    }

    fun dateConverter(str: String): String {
        var date: Date? = null
        try {
            date = SimpleDateFormat("yyyy-MM-dd",Locale("id","ID")).parse(str)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return SimpleDateFormat("dd MMM yyyy",Locale("id","ID")).format(date)
    }

}