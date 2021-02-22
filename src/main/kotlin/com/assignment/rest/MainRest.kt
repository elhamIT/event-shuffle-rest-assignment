package main.kotlin.com.assignment.rest

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import main.kotlin.com.assignment.configuration.DatabaseFactory
import main.kotlin.com.assignment.logging.getLogger
import main.kotlin.com.assignment.services.EventService
import org.slf4j.Logger


fun Application.restService(
    eventService: EventService,
    logger: Logger
){
    DatabaseFactory.init()
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        route("/api/v1/event") {
            eventShuffleRoute(eventService, logger)
        }
    }
}

class MainRest (
    private val port: Int,
    private val eventService: EventService
    ){

    companion object{
        private val logger = getLogger<MainRest>()
    }

    fun start() {
        val engine = embeddedServer(Netty, port) {
            restService(eventService, logger)
        }
        engine.start(wait = false)
    }
}