package main.kotlin.com.assignment.database

import main.kotlin.com.assignment.configuration.DatabaseFactory.query
import main.kotlin.com.assignment.database.data.EventToAdd
import main.kotlin.com.assignment.database.data.VotesToAdd
import main.kotlin.com.assignment.database.model.*
import main.kotlin.com.assignment.database.table.EventDates
import main.kotlin.com.assignment.database.table.Events
import main.kotlin.com.assignment.database.table.Votes
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime

class EventShufflePersistenceImpl(
    private val db: Database
): EventShufflePersistence {

    override fun getAllEvents(): List<Event>? {
        return query(db) {
            Events.selectAll().filterNotNull()
        }.map { Event.from(it) }
    }

    override fun getEventById(id: Long): Event? {
        return query(db) {
            Events.select{
                Events.id eq id
            }.map { Event.from(it) }.firstOrNull()
        }
    }

    override fun getEventByName(name: String): Event? {
        return query(db) {
            Events.select{
                Events.eventName eq name
            }.map { Event.from(it) }.firstOrNull()
        }
    }

    override fun insertEventByName(name: String): Long {
        return query(db) {
            Events.insert{
                it[eventName] = name
            } get Events.id
        }
    }

    override fun insertEventWithDates(eventToAdd: EventToAdd): Long {
        val eventId = insertEventByName(eventToAdd.name)
        query(db) {
            EventDates.batchInsert(eventToAdd.dates.asIterable()) { date ->
                this[EventDates.eventId] = eventId
                this[EventDates.date] = date
            }
        }
        return eventId
    }

    override fun insertVote(id: Long, voteToAdd: DateTime, voter: String) {
        query(db) {
            Votes.insert {
                it[eventId] = id
                it[date] = voteToAdd
                it[Votes.voter] = voter
            } get Votes.id
        }
    }

    override fun getEventDatesByEventId(id: Long): List<EventDate>? {
        return query(db) {
            EventDates.select{
                EventDates.eventId eq id
            }.map { EventDate.from(it) }
        }
    }

    override fun getVotesByDateAndEventId(evetDate: DateTime, id: Long): List<Vote>? {
        return query(db) {
            Votes.select{
                Votes.eventId eq id and (Votes.date eq evetDate)
            }.map { Vote.from(it) }
        }
    }

    override fun getVoteByDateAndEventIdAndVoter(evetDate: DateTime, id: Long, person: String): Vote? {
        return query(db) {
            Votes.select{
                Votes.eventId eq id and (Votes.date eq evetDate) and (Votes.voter eq person)
            }.map { Vote.from(it) }.firstOrNull()
        }
    }
}