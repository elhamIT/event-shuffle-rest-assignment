package main.kotlin.com.assignment.rest

import exception.RequestException
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import main.kotlin.com.assignment.database.model.EventToAdd
import main.kotlin.com.assignment.database.model.VotesToAdd
import org.slf4j.Logger

suspend fun resolveRequest (call: ApplicationCall, request: suspend () -> String) {
    try {
        val response = request()
        call.respondText(response, ContentType.Application.Json, HttpStatusCode.OK)
    } catch (e: RequestException) {
        call.respondText(e.message, ContentType.Text.Plain, e.statusCode)
    }
}

@Throws(RequestException::class)
suspend fun receiveEventData(call: ApplicationCall, logger: Logger): EventToAdd {
    return try {
        val json = call.receiveText()
        EventToAdd.fromJson(json)
    } catch (e: Exception) {
        logger.error("Unable to get data from request body", e)
        throw RequestException(e.message?:"", HttpStatusCode.BadRequest)
    }
}

@Throws(RequestException::class)
suspend fun receiveVoteData(call: ApplicationCall, logger: Logger): VotesToAdd {
    return try {
        val json = call.receiveText()
        VotesToAdd.fromJson(json)
    } catch (e: Exception) {
        logger.error("Unable to get data from request body", e)
        throw RequestException(e.message?:"", HttpStatusCode.BadRequest)
    }
}

@Throws(RequestException::class)
fun getLongFromParameter(parameters: Parameters, parameterName: String): Long {
    return try {
        parameters[parameterName]!!.toLong()
    }catch (e: Exception) {
        throw RequestException("Error getting the id parameter", HttpStatusCode.BadRequest)
    }
}