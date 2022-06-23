package com.financials.stubhubevents.business.utils

import android.content.Context
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName : String) : String? {
    val jsonString : String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (exception : IOException) {
        exception.printStackTrace()
        return null
    }
    return jsonString
}

