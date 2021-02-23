package main.kotlin.com.assignment.database

import main.kotlin.com.assignment.database.model.Event
import main.kotlin.com.assignment.database.model.Vote

interface EventShufflePersistence {
    fun getAll(): List<Event>?
    fun getByName(name: String): Event?
    fun insertVote(item: Vote): String
}