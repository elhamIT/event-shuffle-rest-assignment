package main.kotlin.com.assignment.database.model

import com.google.gson.*
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/*
example body:

{
  "name": "Jake's secret party",
  "dates": [
    "2014-01-01",
    "2014-01-05",
    "2014-01-12"
  ]
}

 */
data class EventToAdd(
    @SerializedName("name")
    val name: String,

    @SerializedName("dates")
    val dates: Array<DateTime>
)  {
    companion object {
        fun fromJson(json: String): EventToAdd {
            val gSon = GsonBuilder().registerTypeAdapter(EventToAdd::class.java, EventsDeserializer()).create()
            return gSon.fromJson(json, EventToAdd::class.java)
        }
    }
}

class EventsDeserializer : JsonDeserializer<EventToAdd> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): EventToAdd {

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        json as JsonObject

        val name = json.get("name").asString
        val datesJson = json.get("dates")
        val dates = if (!datesJson.isJsonNull) {
            datesJson.asJsonArray.map {
                DateTime(formatter.parse(it.toString()))
            }
        } else emptyList()

        return EventToAdd(name, dates.toTypedArray())
    }
}