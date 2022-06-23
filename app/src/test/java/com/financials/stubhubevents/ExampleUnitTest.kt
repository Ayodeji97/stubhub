package com.financials.stubhubevents

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import com.financials.stubhubevents.business.model.sample.Welcome
import com.financials.stubhubevents.business.utils.Constants.JSON_FILE_NEW_NAME
import com.financials.stubhubevents.business.utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.*
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private var eventList = arrayListOf<WelcomeEvent>()

    private val context: Context = ApplicationProvider.getApplicationContext()
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    fun sample(welcome: Welcome) : List<WelcomeEvent> {
        if (welcome.children.isEmpty()) {
            eventList.addAll(welcome.events)
        }

        for (child in welcome.children) {
            Log.i("Events", "EnterHere2 : $child")
            Log.i("Events", "EnterHere3 : ${sample(child)}")
           // eventsList.add(child.events)
            sample(child)
        }
        return emptyList()
    }


    fun getAllEventNew(): Welcome {
        val jsonFileToString = getJsonDataFromAsset(context, JSON_FILE_NEW_NAME)

        val eventListType = object : TypeToken<Welcome>() {}.type

        Log.d("Events", "Events:$eventListType")
        val gson = Gson()
        return gson.fromJson(jsonFileToString, eventListType)
    }

    @Test
    fun tired () {
        val list = getAllEventNew()
        sample(list)
        Log.i("EventsList", "$list")
    }


}