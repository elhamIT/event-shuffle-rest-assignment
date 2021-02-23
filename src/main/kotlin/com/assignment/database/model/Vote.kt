package main.kotlin.com.assignment.database.model

import main.kotlin.com.assignment.database.table.Events
import main.kotlin.com.assignment.database.table.Votes
import org.jetbrains.exposed.sql.ResultRow
import org.joda.time.DateTime

data class Vote(
    val id: Long,
    val eventId: Long,
    val date: DateTime,
    val voter: String
) {
    companion object {
        fun from(row: ResultRow): Vote =
            Vote(
                id = row[Votes.id],
                eventId = row[Votes.eventId],
                date = row[Votes.date],
                voter = row[Votes.voter]
            )
    }

}