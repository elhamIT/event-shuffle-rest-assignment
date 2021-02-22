package main.kotlin.com.assignment.database.table

import org.jetbrains.exposed.sql.Table

object Events : Table("EVENTS") {
    val id = long("ID").autoIncrement()
    override val primaryKey: PrimaryKey = PrimaryKey(id, name = "event_pk")
    val eventName = varchar("EVENT_NAME", 255)
}