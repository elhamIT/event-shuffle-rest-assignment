package main.kotlin.com.assignment.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory : ()-> Database {

    override fun invoke(): Database {
        val config = HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER")
        config.jdbcUrl = System.getenv("JDBC_DATABASE_URL")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        val user = System.getenv("DB_USER")
        if (user != null) {
            config.username = user
        }
        val password = System.getenv("DB_PASSWORD")
        if (password != null) {
            config.password = password
        }
        config.validate()
        return fromDataSource(HikariDataSource(config))
    }

    private fun fromDataSource(dataSource: HikariDataSource): Database {
        val db by lazy {
            Database.connect(dataSource)
        }
        return db
    }

    fun <T> query(db: Database, query: () -> T): T =
        transaction(db) {
            addLogger(StdOutSqlLogger)
            query()
        }

    fun createTables(tables: List<Table>) {
        tables.forEach { table ->
        transaction {
            SchemaUtils.createMissingTablesAndColumns(table)
        }
        }
    }
}

