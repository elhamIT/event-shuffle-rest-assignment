package main.kotlin.com.assignment.database.model

import com.google.gson.annotations.SerializedName
import main.kotlin.com.assignment.gson.gson
import org.joda.time.DateTime




/**

example:
{
"name": "Dick",
"votes": ["2014-01-01","2014-01-05"]
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
            return gson.fromJson(json, VotesToAdd::class.java)
        }
    }
}
