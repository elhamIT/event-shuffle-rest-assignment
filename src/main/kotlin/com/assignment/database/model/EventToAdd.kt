package main.kotlin.com.assignment.database.model

import com.google.gson.annotations.SerializedName
import main.kotlin.com.assignment.gson.gson
import org.joda.time.DateTime


/**

 example:
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
)  {
    companion object {
        fun fromJson(json: String): EventToAdd {
            return gson.fromJson(json, EventToAdd::class.java)
        }
    }
}