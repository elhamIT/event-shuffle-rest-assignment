package main.kotlin.com.assignment.exception

import io.ktor.http.*
import java.lang.Exception

class RequestException(
    override val message: String,
    val statusCode: HttpStatusCode
): Exception(message) {
}