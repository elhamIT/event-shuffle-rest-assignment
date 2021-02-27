package main.kotlin.com.assignment.services

import main.kotlin.com.assignment.database.model.*

interface EventService {
    fun getAllEvents(): List<Event>?
    fun addEventWithDates(eventToAdd: EventToAdd): Long
    fun showEventWithDetails(eventId: Long): DetailedEvent?
    fun addVotesToEvent(eventId: Long, votesToAdd: VotesToAdd): DetailedEvent?
    fun showEventResults(eventId: Long): EventResults?
}