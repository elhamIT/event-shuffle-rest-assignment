package main.kotlin.com.assignment.services

import main.kotlin.com.assignment.database.model.Event
import main.kotlin.com.assignment.database.model.EventToAdd
import main.kotlin.com.assignment.database.model.VoteToAdd

interface EventService {
    fun getAllEvents(): List<Event>?
    fun addEventWithDates(eventToAdd: EventToAdd): Int
    fun showEventWithDetails(eventId: Long): String
    fun addVotesToEvent(eventId: Long, voteToAdd: VoteToAdd): String
}