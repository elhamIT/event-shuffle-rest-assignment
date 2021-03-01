package main.kotlin.com.assignment.exception

import java.lang.Exception

class JsonParseException(
    override val message: String
): Exception(message) {
}