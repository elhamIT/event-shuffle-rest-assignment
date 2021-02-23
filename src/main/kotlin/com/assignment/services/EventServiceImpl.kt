package main.kotlin.com.assignment.services

import main.kotlin.com.assignment.database.EventShufflePersistence
import main.kotlin.com.assignment.database.model.Event
import main.kotlin.com.assignment.database.model.EventToAdd
import main.kotlin.com.assignment.database.model.VoteToAdd

class EventServiceImpl(
    private val eventShufflePersistence: EventShufflePersistence
) : EventService {
    override fun getAllEvents(): List<Event>? {
        return eventShufflePersistence.getAll()
    }

    override fun addEventWithDates(eventToAdd: EventToAdd): Int {
        val event = eventShufflePersistence.getByName(eventToAdd.name)
        return 0
    }

    override fun showEventWithDetails(eventId: Long): String {
        TODO("Not yet implemented")
    }

    override fun addVotesToEvent(eventId: Long, voteToAdd: VoteToAdd): String {
        TODO("Not yet implemented")
    }
}