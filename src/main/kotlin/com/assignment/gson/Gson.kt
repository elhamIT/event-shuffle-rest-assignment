package main.kotlin.com.assignment.gson

import com.google.gson.GsonBuilder
import org.joda.time.DateTime
import org.joda.time.LocalDateTime

val gson = GsonBuilder()
    .registerTypeAdapter(DateTime::class.java, DateTimeDeserializer())
    .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeSerializer())
    .create()