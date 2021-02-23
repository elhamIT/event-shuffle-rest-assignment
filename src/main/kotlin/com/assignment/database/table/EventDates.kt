package main.kotlin.com.assignment.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object EventDates : Table("EVENT_DATES") {
    val id = long("ID").autoIncrement()
    val eventId = long("EVENT_ID") references Events.id
    val date = datetime("EVENT_DATE")
}