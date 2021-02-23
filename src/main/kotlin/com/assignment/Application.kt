package main.kotlin.com.assignment

import main.kotlin.com.assignment.configuration.DatabaseFactory
import main.kotlin.com.assignment.database.EventShufflePersistence
import main.kotlin.com.assignment.database.EventShufflePersistenceImpl
import main.kotlin.com.assignment.logging.getLogger
import main.kotlin.com.assignment.rest.MainRest
import main.kotlin.com.assignment.services.EventService
import main.kotlin.com.assignment.services.EventServiceImpl
import org.jetbrains.exposed.sql.Database


class Application {
    companion object {
        private val logger = getLogger<Application>()

        @JvmStatic
        fun main(args: Array<String>) {
            Application().start()
        }
    }

    private val database: Database = DatabaseFactory()
    private val eventShufflePersistence: EventShufflePersistence = EventShufflePersistenceImpl(database)
    private val eventService: EventService = EventServiceImpl(eventShufflePersistence)
    private val mainRest = MainRest(5000, eventService)

    fun start() {
                logger.info("Starting EventShuffleRestAPI...")
                mainRest.start()
    }
}