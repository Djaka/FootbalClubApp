package com.djakapermana.footbalclubapp.helper

import com.djakapermana.footbalclubapp.helper.GeneralFunctions.dateConverter
import com.djakapermana.footbalclubapp.helper.GeneralFunctions.timeFormater
import junit.framework.Assert.assertEquals
import org.junit.Test

class GeneralFunctionsTest {

    @Test
    fun testTimeFormater() {
        val str = "20:25:23"
        val convertTime = timeFormater(str)
        assertEquals("20:25",convertTime)
    }

    @Test
    fun testDateFormatter() {
        val str = "2018-08-20"
        val convertDate = dateConverter(str)
        assertEquals("20 Agu 2018",convertDate)
    }

}