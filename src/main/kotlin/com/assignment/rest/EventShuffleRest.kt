package main.kotlin.com.assignment.rest

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import main.kotlin.com.assignment.database.data.DetailedEvent
import main.kotlin.com.assignment.database.data.EventResults
import main.kotlin.com.assignment.services.EventService
import org.slf4j.Logger

fun Route.eventShuffleRoute(
    eventService: EventService,
    logger: Logger
) {

    /**
    Test if service works
     */
    get("/test") {
        call.respondText("SERVICE WORKS!", contentType = ContentType.Text.Plain)
    }

    /**
    GET list of events
     */
    get("/list") {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        resolveRequest(call) {
            val events = eventService.getAllEvents()
            Gson().toJson(events)
        }
    }

    /**
    POST an event with its dates
     */
    post() {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        return@post resolveRequest(call) {
            val eventToAdd = receiveEventData(call, logger)
            val eventId = eventService.addEventWithDates(eventToAdd)
            EventResponse(id = eventId).toJson()
        }
    }

    /**
   GET details of an event (event name, given dates and votes)
    */
    get("/{id}") {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        resolveRequest(call) {
            val id = getLongFromParameter(call.parameters, "id")
            val detailedEvent = eventService.showEventWithDetails(id)
            DetailedEvent.toJson(detailedEvent)
        }
    }

    /**
    POST votes of a voter for dates of an event
     */
    post("/{id}/vote") {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        return@post resolveRequest(call) {
            val id = getLongFromParameter(call.parameters, "id")
            val votesToAdd = receiveVoteData(call, logger)
            val detailedEvent = eventService.addVotesToEvent(id, votesToAdd)
            DetailedEvent.toJson(detailedEvent)
        }
    }

    /**
    GET results of an event (an event with dates that are suitable for all participants.)
     */
    get("/{id}/results") {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        resolveRequest(call) {
            val id = getLongFromParameter(call.parameters, "id")
            val eventResults = eventService.showEventResults(id)
            EventResults.toJson(eventResults)
        }
    }
}

data class EventResponse(
    val id: Long
) {
    fun toJson(): String = Gson().toJson(this)
}