package main.kotlin.com.assignment.database.model

import com.google.gson.*
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import java.lang.reflect.Type
import java.util.*
import java.text.SimpleDateFormat




/*
example body:

{
  "name": "Dick",
  "votes": [
    "2014-01-01",
    "2014-01-05"
  ]
}

 */
data class VotesToAdd(
    @SerializedName("name")
    val name: String,

    @SerializedName("votes")
    val votes: Array<DateTime>
) {
    companion object {
        fun fromJson(json: String): VotesToAdd {
            val gSon = GsonBuilder().registerTypeAdapter(VotesToAdd::class.java, VoteDeserializer()).create()
            return gSon.fromJson(json, VotesToAdd::class.java)
        }
    }
}

class VoteDeserializer : JsonDeserializer<VotesToAdd> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): VotesToAdd {

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        json as JsonObject

        val name = json.get("name").asString
        val votesJson = json.get("votes")
        val votes = if (!votesJson.isJsonNull) {
            votesJson.asJsonArray.map {
                DateTime(formatter.parse(it.toString()))
            }
        } else emptyList()

        return VotesToAdd(name, votes.toTypedArray())
    }
}