package main.kotlin.com.assignment.services

import main.kotlin.com.assignment.database.EventShufflePersistence
import main.kotlin.com.assignment.database.data.*
import main.kotlin.com.assignment.database.model.*
import org.joda.time.LocalDateTime
import java.util.stream.Collectors

import java.util.stream.IntStream




class EventServiceImpl(
    private val eventShufflePersistence: EventShufflePersistence
) : EventService {

    /**
     * Gets list of events from database
     */
    override fun getAllEvents(): List<Event>? {
        return eventShufflePersistence.getAllEvents() ?: emptyList()
    }

    /**
     * Adds an event to database with its dates
     */
    override fun addEventWithDates(eventToAdd: EventToAdd): Long {
        val event = eventShufflePersistence.getEventByName(eventToAdd.name)
            ?: return eventShufflePersistence.insertEventWithDates(eventToAdd)
        return event.id
    }

    /**
     * Gets an event with its details (dates and votes)
     */
    override fun showEventWithDetails(eventId: Long): DetailedEvent? {
        val event = eventShufflePersistence.getEventById(eventId)
        val eventDates = eventShufflePersistence.getEventDatesByEventId(eventId)
        val votesToShow = if (!eventDates.isNullOrEmpty()) eventDates.map { eventDate ->
            val votes = eventShufflePersistence.getVotesByDateAndEventId(eventDate.date, eventId)
            VoteToShow(
                date = eventDate.date.toLocalDateTime(),
                people = (if (!votes.isNullOrEmpty()) votes.map { vote -> vote.voter } as ArrayList<String> else ArrayList<String>())
            )
        } else emptyList()

        if (event != null){
            return DetailedEvent(
                id = event.id,
                eventName = event.eventName,
                dates = (if (!eventDates.isNullOrEmpty()) eventDates.map { eventDate -> eventDate.date.toLocalDateTime() } else emptyList())
                        as ArrayList<LocalDateTime>,
                votes = votesToShow as ArrayList<VoteToShow>
            )
        }
        return null
    }

    /**
     * Adds a vote of voter to an event and returns updated event with details (if the vote does not exist)
     */
    override fun addVotesToEvent(eventId: Long, votesToAdd: VotesToAdd): DetailedEvent? {
        val event = eventShufflePersistence.getEventById(eventId)
        if (event != null) {
            votesToAdd.votes.forEach{ vote ->
                val existingVote = eventShufflePersistence.getVoteByDateAndEventIdAndVoter(vote, eventId, votesToAdd.name)
                if (existingVote == null){
                    eventShufflePersistence.insertVote(eventId, vote, votesToAdd.name)
                }
            }
            return showEventWithDetails(eventId)
        }
        return null
    }

    /**
     * Shows event with suitable dates for all participants
     * (date with maximum voters, or if different dates have equal number of voters returns them all)
     */
    override fun showEventResults(eventId: Long): EventResults? {
        val eventDetailed = showEventWithDetails(eventId)
        if (eventDetailed != null){
            val highestVote = eventDetailed.votes.maxWithOrNull { vote1, vote2 ->
                vote1.people.count().compareTo(vote2.people.count())
            }
            //This is for handling case in which there are equal number of votes for different dates
            if (highestVote != null) {
                val suitableVotesIndexes = IntStream.range(0, eventDetailed.votes.size)
                    .filter { index: Int -> eventDetailed.votes[index].people.count().compareTo(highestVote.people.count()) === 0 }
                    .boxed()
                    .collect(Collectors.toList())
                val suitableVotes = suitableVotesIndexes.map { voteIndex -> eventDetailed.votes[voteIndex] }
                return EventResults(

                    id = eventId,
                    eventName = eventDetailed.eventName,
                    suitableDates = suitableVotes as ArrayList<VoteToShow?>
                )
            }
        }
        return null
    }
}