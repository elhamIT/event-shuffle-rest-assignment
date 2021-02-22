package main.kotlin.com.assignment.database.table

import main.kotlin.com.assignment.database.table.Events.autoIncrement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.CurrentDateTime
import org.jetbrains.exposed.sql.jodatime.datetime

object EventDates : Table("EVENT_DATES") {
    val eventId = long("EVENT_ID") references Events.id
    val date = datetime("EVENT_DATE").defaultExpression(CurrentDateTime())
}