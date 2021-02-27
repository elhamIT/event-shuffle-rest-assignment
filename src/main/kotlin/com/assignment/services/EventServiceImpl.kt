package main.kotlin.com.assignment.services

import main.kotlin.com.assignment.database.EventShufflePersistence
import main.kotlin.com.assignment.database.model.*

class EventServiceImpl(
    private val eventShufflePersistence: EventShufflePersistence
) : EventService {
    override fun getAllEvents(): List<Event>? {
        return eventShufflePersistence.getAllEvents()
    }

    override fun addEventWithDates(eventToAdd: EventToAdd): Long {
        val event = eventShufflePersistence.getEventByName(eventToAdd.name)
            ?: return eventShufflePersistence.insertEventWithDates(eventToAdd)
        return event.id
    }

    override fun showEventWithDetails(eventId: Long): DetailedEvent? {
        val event = eventShufflePersistence.getEventById(eventId)
        val eventDates = eventShufflePersistence.getEventDatesByEventId(eventId)
        val votesToShow = if (!eventDates.isNullOrEmpty()) eventDates.map { eventDate ->
            val votes = eventShufflePersistence.getVotesByDateAndEventId(eventDate.date, eventId)
            VoteToShow(
                date = eventDate.date.toLocalDateTime(),
                people = if (!votes.isNullOrEmpty()) votes.map { vote -> vote.voter } else emptyList()
            )
        } else emptyList()

        if (event != null){
            return DetailedEvent(
                id = event.id,
                eventName = event.eventName,
                dates = if (!eventDates.isNullOrEmpty()) eventDates.map { eventDate -> eventDate.date.toLocalDateTime() } else emptyList(),
                votes = votesToShow
                )
        }
        return null
    }

    override fun addVotesToEvent(eventId: Long, votesToAdd: VotesToAdd): DetailedEvent? {
        val event = eventShufflePersistence.getEventById(eventId)
        if (event != null) {
            eventShufflePersistence.insertVote(eventId, votesToAdd)
            showEventWithDetails(eventId)
        }
        return null
    }

    override fun showEventResults(eventId: Long): EventResults? {
        TODO("Not yet implemented")
    }
}