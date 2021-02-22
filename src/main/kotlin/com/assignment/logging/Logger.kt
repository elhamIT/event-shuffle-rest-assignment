package main.kotlin.com.assignment.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/*
Get a logger
 */

inline fun <reified T> getLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}