package main.kotlin.com.assignment.database

import main.kotlin.com.assignment.database.model.Event
import main.kotlin.com.assignment.database.model.Vote
import org.jetbrains.exposed.sql.Database

class EventShufflePersistenceImpl(
    private val db: Database
): EventShufflePersistence {
    override fun getAll(): List<Event>? {
        TODO("Not yet implemented")
    }

    override fun insertVote(item: Vote): String {
        TODO("Not yet implemented")
    }
}