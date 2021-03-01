package main.kotlin.com.assignment.database.data

import main.kotlin.com.assignment.gson.gson
import org.joda.time.LocalDateTime


/**
 *
example:
{
  "id": 0,
  "name": "Jake's secret party",
  "dates": [
    "2014-01-01",
    "2014-01-05",
    "2014-01-12"
  ],
  "votes": [
    {
      "date": "2014-01-01",
      "people": [
        "John",
        "Julia",
        "Paul",
        "Daisy",
        "Dick"
      ]
    },
    {
      "date": "2014-01-05",
      "people": [
        "Dick"
      ]
    }
  ]
}

 */

data class DetailedEvent(
    val id: Long,
    val eventName: String,
    val dates: ArrayList<LocalDateTime>,
    val votes:ArrayList<VoteToShow>
) {
    companion object {
        fun toJson(detailedEvent: DetailedEvent?): String {
            return gson.toJson(detailedEvent, DetailedEvent::class.java)
        }
    }
}
