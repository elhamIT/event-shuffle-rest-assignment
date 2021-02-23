package main.kotlin.com.assignment.database.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

/*
example body:

 */
data class VoteToAdd(
    @SerializedName("voter")
    val voter: String,

    @SerializedName("dates")
    val dates: Array<DateTime>
) {

    companion object {
        fun formJson(json: String): VoteToAdd {
            return Gson().fromJson(json, VoteToAdd::class.java)
        }
    }
}