package main.kotlin.com.assignment.database.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

/*
example body:

{
  "name": "Jake's secret party",
  "dates": [
    "2014-01-01",
    "2014-01-05",
    "2014-01-12"
  ]
}


 */
data class EventToAdd(
    @SerializedName("name")
    val name: String,

    @SerializedName("dates")
    val dates: Array<DateTime>
) {

   companion object {
       fun formJson(json: String): EventToAdd {
           return Gson().fromJson(json, EventToAdd::class.java)
       }
   }
}