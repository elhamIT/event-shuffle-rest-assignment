package main.kotlin.com.assignment.services

import main.kotlin.com.assignment.database.model.DetailedEvent
import main.kotlin.com.assignment.database.model.Event
import main.kotlin.com.assignment.database.model.EventToAdd
import main.kotlin.com.assignment.database.model.VotesToAdd

interface EventService {
    fun getAllEvents(): List<Event>?
    fun addEventWithDates(eventToAdd: EventToAdd): Long
    fun showEventWithDetails(eventId: Long): DetailedEvent?
    fun addVotesToEvent(eventId: Long, votesToAdd: VotesToAdd): DetailedEvent?
}