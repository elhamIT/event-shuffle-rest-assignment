package test.kotlin.com.assignment.rest

import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockk
import main.kotlin.com.assignment.gson.gson
import main.kotlin.com.assignment.logging.getLogger
import main.kotlin.com.assignment.rest.restService
import main.kotlin.com.assignment.services.EventService
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class EventShuffleRestTest {

    val eventService = mockk<EventService>()
    val logger = getLogger<EventShuffleRestTest>()


    @Test
    fun `Valid request to get events` (){
        val amount = Random.nextInt(5)
        withTestApplication({
            every{ eventService.getAllEvents() } returns
                    generateSampleEventsList(amount)
            restService(eventService, logger)
        }) {
            handleRequest(HttpMethod.Get, "/api/v1/event/list", ).apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val events = JSONArray(response.content)
                assertEquals(amount, events.length())
            }
        }
    }

    @Test
    fun `Valid request to add event with dates` (){
        val eventToAdd = generateSampleEventWithDates()
        withTestApplication({
            every{ eventService.addEventWithDates(any()) } answers {1}
            restService(eventService, logger)
        }) {
            handleRequest(HttpMethod.Post, "/api/v1/event") {
                setBody(gson.toJson(eventToAdd))
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val eventId = JSONObject(response.content)
                assertEquals(1, eventId["id"])
            }
        }
    }

    @Test
    fun `Valid request to show event with details` (){
        withTestApplication({
            every{ eventService.showEventWithDetails(1) } answers {
                generateSampleDetailedEvent()
            }
            restService(eventService, logger)
        }) {
            handleRequest(HttpMethod.Get, "/api/v1/event/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `Valid request to add votes of a voter for dates of an event` (){
        val voteToAdd = generateSampleVotesForEvent()
        withTestApplication({
            every{ eventService.addVotesToEvent(1, any()) } answers {
                generateSampleDetailedEvent()
            }
            restService(eventService, logger)
        }) {
            handleRequest(HttpMethod.Post, "/api/v1/event/1/vote") {
                setBody(gson.toJson(voteToAdd))
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `Valid request to show event results` (){
        withTestApplication({
            every{ eventService.showEventResults(1) } answers {
                generateSampleEventResults()
            }
            restService(eventService, logger)
        }) {
            handleRequest(HttpMethod.Get, "/api/v1/event/1/results").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}