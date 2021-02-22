package main.kotlin.com.assignment.services

import main.kotlin.com.assignment.database.model.EventToAdd

interface EventService {
    fun getAllEvents(): String
    fun addEvent(eventToAdd: EventToAdd): String
}