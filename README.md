#Eventshuffle backend API

Eventshuffle is an application to help scheduling events with friends, quite like http://doodle.com/ but in a much simplified way.<br>

An event is created by posting a name and suitable dates to the backend, events can be queried from the backend and participants can submit dates suitable for them.

##Tech stack

- Kotlin as programming language
- Ktor for rest API implementation
- Exposed as SQL framework
- Postgres as database
- HikariCP as JDBC Connection Pool

## Running the project

1. clone the repo
2. you need to have postgres running on OS and create database called `events` (you can use `pgadmin`)
3. set the `JDBC_DRIVER, JDBC_DATABASE_URL, DB_USER and DB_PASSWORD` environment variables (you can get them from `local-environment.env` and set them in `Edit Configuration`)
4. build with gradle and run the project `Appplication.kt`
5. Access to `http://127.0.0.1:5000/`

## API Documentation

The API will be accessed through `http://127.0.0.1:5000/api/v1/event` using `curl` or `Postman`

### Test the service works
`GET /test` <br/>
Responds with `SERVICE WORKS!`. It is just to be sure service works.

### Get list of events
`GET /list`
Requires no parameters and returns list of `Event` json representation:
```
{
  "events": [
    {
      "id": 0,
      "name": "Jake's secret party"
    },
    {
      "id": 1,
      "name": "Bowling night"
    },
    {
      "id": 2,
      "name": "Tabletop gaming"
    }
  ]
}
```

### Create an event with dates
`POST`
Adds an event with its dates.<br>
Requires no parameters.<br>
Expects a `EventToAdd` json representation as request body:
````
{
  "name": "Jake's secret party",
  "dates": [
    "2014-01-01",
    "2014-01-05",
    "2014-01-12"
  ]
}
````
Returns created event's `id` of json representation:
```
{
  "id": 0
}
```

### Get details of an event (name, dates and votes)
`GET /{id}`
Requires an event's id path parameter as a long value.
Returns json representation of `DetailedEvent`:
```
{
  "id": 0,
  "name": "Jake's secret party",
  "dates": [
    "2014-01-01",
    "2014-01-05",
    "2014-01-12"
  ],
  "votes": [
    {
      "date": "2014-01-01",
      "people": [
        "John",
        "Julia",
        "Paul",
        "Daisy"
      ]
    }
  ]
}
```

### Add votes of a voter for dates of an event
`POST /{id}/vote`
Requires an event's id path parameter as a long value.
Expects a `VotesToAdd` json representation as request body.
````
{
  "name": "Dick",
  "votes": [
    "2014-01-01",
    "2014-01-05"
  ]
}
````
Returns json representation of `DetailedEvent` after the votes are added.

### Get the results of an event
`GET /{id}/results`
Requires an event's id path parameter as a long value.
Returns json representation of `EventResults` which indicates the dates that are suitable for all participants.



