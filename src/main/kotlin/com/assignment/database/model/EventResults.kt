package main.kotlin.com.assignment.database.model

import com.google.gson.Gson
import org.joda.time.DateTime

/**
 *
 example:
 {
     "id": 0,
     "name": "Jake's secret party",
     "suitableDates": [
     {
     "date": "2014-01-01",
     "people": [
     "John",
     "Julia",
     "Paul",
     "Daisy",
     "Dick"
     ]
     }
     ]
 }
 */

data class EventResults(
    val id: Long,
    val eventName: String,
    val suitableDates:List<VoteToShow>
) {
    companion object {
        fun toJson(eventResults: EventResults): String {
            return Gson().toJson(eventResults, EventResults::class.java)
        }
    }
}
