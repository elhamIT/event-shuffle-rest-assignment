package test.kotlin.com.assignment.rest

import main.kotlin.com.assignment.database.data.*
import main.kotlin.com.assignment.database.model.*
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import java.text.SimpleDateFormat

fun generateSampleEventsList(amount: Int): List<Event> =
    IntRange(1, amount).map {
        Event(
            id = it.toLong(),
            eventName = "Event $it"
        )
    }

fun generateSampleEventWithDates(): EventToAdd =
    EventToAdd(
        name = "Sample event Party",
        dates = arrayListOf(
            DateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01"))),
            DateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-05"))),
            DateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-10")))
        )
    )

fun generateSampleDetailedEvent(): DetailedEvent =
    DetailedEvent(
        id = 1,
        eventName = "Sample event Party",
        dates = arrayListOf(
            LocalDateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01"))),
            LocalDateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-05"))),
            LocalDateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-10")))
        ),
        votes = arrayListOf(
            VoteToShow(
                date = LocalDateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01"))),
                people = arrayListOf("Elham", "John")
            ),
            VoteToShow(
                date = LocalDateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-10"))),
                people = arrayListOf("Elham")
            )
        )
    )

fun generateSampleVotesForEvent(): VotesToAdd =
    VotesToAdd(
        name = "Elham",
        votes = arrayListOf(
            DateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01"))),
            DateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-10")))
        )
    )

fun generateSampleEventResults(): EventResults =
    EventResults(
        id = 1,
        eventName = "Sample event Party",
        suitableDates = arrayListOf(
            VoteToShow(
                date = LocalDateTime(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01"))),
                people = arrayListOf("Elham", "John")
            )
        )
    )