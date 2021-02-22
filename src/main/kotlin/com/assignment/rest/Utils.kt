package main.kotlin.com.assignment.rest

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import main.kotlin.com.assignment.database.model.EventToAdd
import org.slf4j.Logger

suspend fun resolveRequest (call: ApplicationCall, request: suspend () -> String) {
    try {
        val response = request()
        call.respondText(response, ContentType.Application.Json, HttpStatusCode.OK)
    } catch (e: Throwable) {
        call.respondText(e.message?: "", ContentType.Text.Plain, HttpStatusCode.BadRequest)
    }
}

suspend fun receiveEventData(call: ApplicationCall, logger: Logger): EventToAdd {
    return try {
        val json = call.receiveText()
        EventToAdd.formJson(json)
    } catch (e: Exception) {
        logger.error("Unable to get data from request body", e)
        throw Exception()
    }
}