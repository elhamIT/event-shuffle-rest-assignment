package main.kotlin.com.assignment.database

import main.kotlin.com.assignment.database.model.*
import org.joda.time.DateTime

interface EventShufflePersistence {
    fun getAllEvents(): List<Event>?
    fun getEventById(id: Long): Event?
    fun getEventByName(name: String): Event?
    fun insertEventByName(name: String): Long
    fun insertEventWithDates(eventToAdd: EventToAdd): Long
    fun insertVote(id: Long, vote: VotesToAdd)
    fun getEventDatesByEventId(id: Long): List<EventDate>?
    fun getVotesByDateAndEventId(eventDate: DateTime, id: Long): List<Vote>?
}