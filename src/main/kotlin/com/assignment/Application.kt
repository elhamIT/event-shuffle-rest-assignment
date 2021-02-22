package main.kotlin.com.assignment

import main.kotlin.com.assignment.logging.getLogger
import main.kotlin.com.assignment.rest.MainRest
import main.kotlin.com.assignment.services.EventService
import main.kotlin.com.assignment.services.EventServiceImpl


class Application {
    companion object {
        private val logger = getLogger<Application>()

        @JvmStatic
        fun main(args: Array<String>) {
            Application().start()
        }
    }

    private val eventService: EventService = EventServiceImpl()
    private val mainRest = MainRest(5000, eventService)

    fun start() {
                logger.info("Starting EventShuffleRestAPI...")
                mainRest.start()
    }
}