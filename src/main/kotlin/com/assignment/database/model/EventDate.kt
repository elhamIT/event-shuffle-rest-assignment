package main.kotlin.com.assignment.database.model

import main.kotlin.com.assignment.database.table.EventDates
import org.jetbrains.exposed.sql.ResultRow
import org.joda.time.DateTime

data class EventDate(
    val id: Long,
    val eventId: Long,
    val date: DateTime
) {
    companion object {
        fun from(row: ResultRow): EventDate =
            EventDate(
                id = row[EventDates.id],
                eventId = row[EventDates.eventId],
                date = row[EventDates.date]
            )
    }

}