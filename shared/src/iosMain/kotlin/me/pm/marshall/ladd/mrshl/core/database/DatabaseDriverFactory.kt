package me.pm.marshall.ladd.mrshl.core.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import me.pm.marshall.ladd.mrshl.database.MrshlDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(MrshlDatabase.Schema, "MrshlDatabase.db")
    }
}
