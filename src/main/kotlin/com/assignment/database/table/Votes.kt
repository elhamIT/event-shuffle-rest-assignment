package main.kotlin.com.assignment.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.Date
import org.jetbrains.exposed.sql.jodatime.datetime

object Votes : Table("VOTES") {
    val id = long("ID").autoIncrement()
    val eventId = long("EVENT_ID") references Events.id
    val date = datetime("VOTED_DATE")
    val voter = varchar("VOTER", 255)
}