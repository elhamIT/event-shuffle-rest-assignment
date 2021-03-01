package main.kotlin.com.assignment.database.model

import main.kotlin.com.assignment.gson.gson

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
    val suitableDates: List<VoteToShow?>?
) {
    companion object {
        fun toJson(eventResults: EventResults?): String {
            return gson.toJson(eventResults, EventResults::class.java)
        }
    }
}
