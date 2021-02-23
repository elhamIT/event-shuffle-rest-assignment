package main.kotlin.com.assignment.rest

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import main.kotlin.com.assignment.services.EventService
import org.slf4j.Logger

fun Route.eventShuffleRoute(
    eventService: EventService,
    logger: Logger
) {

    /*
    Test if service works
     */
    get("/test") {
        call.respondText("SERVICE WORKS!", contentType = ContentType.Text.Plain)
    }

    /*
    GET list of events
     */
    get("/list") {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        resolveRequest(call) {
            val events = eventService.getAllEvents()
            Gson().toJson(events)
        }
    }

    /*
    POST an event with suggested dates
     */
    post() {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        return@post resolveRequest(call) {
            val eventToAdd = receiveEventData(call, logger)
            val event = eventService.addEventWithDates(eventToAdd)
            Gson().toJson(event)
        }
    }

    /*
   GET details of events (event name, given dates and votes)
    */
    get("/{id}") {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        resolveRequest(call) {
            val id = getLongFromParameter(call.parameters, "id")
            val detailedEvent = eventService.showEventWithDetails(id)
            Gson().toJson(detailedEvent)
        }
    }

    /*
    POST votes of a voter for dates for an event
     */
    post("/{id}/vote") {
        logger.debug("${call.request.httpMethod} to ${call.request.uri}")
        return@post resolveRequest(call) {
            val id = getLongFromParameter(call.parameters, "id")
            val votesToAdd = receiveVoteData(call, logger)
            eventService.addVotesToEvent(id, votesToAdd)
            val detailedEvent = eventService.showEventWithDetails(id)
            Gson().toJson(detailedEvent)
        }
    }
}