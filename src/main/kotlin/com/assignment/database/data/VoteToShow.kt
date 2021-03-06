package main.kotlin.com.assignment.database.data

import org.joda.time.LocalDateTime

/**
 example:
 {
 "date": "2014-01-01",
 "people": [
 "John",
 "Julia",
 "Paul",
 "Daisy",
 "Dick"
 ]
 }
 */
data class VoteToShow(
 val date: LocalDateTime,
 val people:ArrayList<String>
)