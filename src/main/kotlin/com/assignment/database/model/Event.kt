package main.kotlin.com.assignment.database.model

import main.kotlin.com.assignment.database.table.Events
import org.jetbrains.exposed.sql.ResultRow

data class Event(
    val eventId: Long,
    val eventName: String
) {
    companion object {
        fun from(row: ResultRow): Event =
            Event(
                eventId = row[Events.eventId],
                eventName = row[Events.eventName]
            )
    }

}