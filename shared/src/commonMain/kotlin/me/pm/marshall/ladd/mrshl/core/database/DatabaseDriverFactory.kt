package me.pm.marshall.ladd.mrshl.core.database

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}
