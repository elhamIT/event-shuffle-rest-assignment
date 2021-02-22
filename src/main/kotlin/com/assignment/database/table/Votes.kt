package main.kotlin.com.assignment.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.CurrentDateTime
import org.jetbrains.exposed.sql.jodatime.datetime

object Votes : Table("VOTES") {
    val eventId = long("EVENT_ID") references Events.id
    val date = datetime("VOTED_DATE").defaultExpression(CurrentDateTime())
    val voter = varchar("VOTER", 255)
}