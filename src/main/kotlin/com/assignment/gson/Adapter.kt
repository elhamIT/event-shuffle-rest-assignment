package main.kotlin.com.assignment.gson

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import java.lang.reflect.Type
import java.text.SimpleDateFormat

class LocalDateTimeSerializer : JsonSerializer<LocalDateTime> {
    override fun serialize(src: LocalDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return JsonPrimitive(formatter.format(inputFormatter.parse(src.toString())))
    }
}

class DateTimeDeserializer : JsonDeserializer<DateTime> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DateTime {
        return DateTime(json?.asString)
    }
}
