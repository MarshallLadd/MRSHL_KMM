package me.pm.marshall.ladd.mrshl.core.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import me.pm.marshall.ladd.mrshl.database.MrshlDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(MrshlDatabase.Schema, context, "MrshlDatabase.db")
    }
}
