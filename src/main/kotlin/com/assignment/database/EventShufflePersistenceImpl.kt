package main.kotlin.com.assignment.database

import main.kotlin.com.assignment.configuration.DatabaseFactory.query
import main.kotlin.com.assignment.database.model.Event
import main.kotlin.com.assignment.database.model.Vote
import main.kotlin.com.assignment.database.table.Events
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class EventShufflePersistenceImpl(
    private val db: Database
): EventShufflePersistence {
    override fun getAll(): List<Event>? {
        return query(db) {
            Events.selectAll()
        }.map { Event.from(it) }
    }

    override fun getByName(name: String): Event? {
        return query(db) {
            Events.select{
                Events.eventName eq name
            }.map { Event.from(it) }.firstOrNull()
        }
    }

    override fun insertVote(item: Vote): String {
        TODO("Not yet implemented")
    }
}