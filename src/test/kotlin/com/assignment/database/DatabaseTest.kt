package test.kotlin.com.assignment.database

import main.kotlin.com.assignment.configuration.DatabaseFactory
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

object TestDatabaseFactory {
    val database by lazy {
        Database.connect("jdbc:h2:mem:test" + this::class.java + ";DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseTest {
    private object table1 : Table("TABLE1") {
        val id = long("ID").autoIncrement()
        val name = varchar("NAME", 255)
    }

    private object table2 : Table("TABLE2") {
        val id = long("ID").autoIncrement()
        val name = varchar("NAME", 255)
        val date = datetime("DATE")
    }

    @Test
    fun `Can get database connection`() {
        TestDatabaseFactory.database
    }

    @Test
    fun `Can create database tables`() {
        val db = TestDatabaseFactory.database
        DatabaseFactory.createTables(listOf(table1, table2))
    }
}