package main.kotlin.com.assignment.database.model

import main.kotlin.com.assignment.database.table.Events
import org.jetbrains.exposed.sql.ResultRow

data class Event(
    val id: Long,
    val eventName: String
) {
    companion object {
        fun from(row: ResultRow): Event =
            Event(
                id = row[Events.id],
                eventName = row[Events.eventName]
            )
    }

}