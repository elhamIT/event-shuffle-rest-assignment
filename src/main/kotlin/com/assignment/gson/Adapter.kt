package main.kotlin.com.assignment.gson

import com.google.gson.*
import main.kotlin.com.assignment.exception.JsonParseException
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.lang.Exception


class LocalDateTimeSerializer : JsonSerializer<LocalDateTime> {
    override fun serialize(src: LocalDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return JsonPrimitive(formatter.format(inputFormatter.parse(src.toString())))
    }
}

class DateTimeDeserializer : JsonDeserializer<DateTime> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DateTime {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return try {
            DateTime(formatter.parse(json?.asString))
        } catch (e: Exception) {
            throw JsonParseException("Unparseable date, date format should be yyyy-MM-dd")
        }
    }
}

class DateTimeSerializer : JsonSerializer<DateTime> {
    override fun serialize(src: DateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return JsonPrimitive(formatter.format(inputFormatter.parse(src.toString())))
    }
}
