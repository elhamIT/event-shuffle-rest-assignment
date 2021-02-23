package main.kotlin.com.assignment.database.model

import com.google.gson.Gson
import org.joda.time.DateTime
import java.util.*

data class DetailedEvent(
    val id: Long,
    val eventName: String,
    val dates: List<DateTime>,
    val votes:List<VoteToShow>
) {
    companion object {
        fun toJson(detailedEvent: DetailedEvent): String {
            return Gson().toJson(detailedEvent, DetailedEvent::class.java)
        }
    }
}

data class VoteToShow(
    val date: DateTime,
    val people:List<String>
)