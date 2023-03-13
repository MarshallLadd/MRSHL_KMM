package me.pm.marshall.ladd.mrshl.android.presentation.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import me.pm.marshall.ladd.mrshl.android.BaseApplication
import me.pm.marshall.ladd.mrshl.core.database.DatabaseDriverFactory
import me.pm.marshall.ladd.mrshl.core.database.sqlDelight.PuzzleDatabaseSqlDelightImpl
import me.pm.marshall.ladd.mrshl.core.network.HttpClientFactory
import me.pm.marshall.ladd.mrshl.core.network.answers.AnswersApiKtorImpl
import me.pm.marshall.ladd.mrshl.database.MrshlDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabaseImpl(database: MrshlDatabase): PuzzleDatabaseSqlDelightImpl {
        return PuzzleDatabaseSqlDelightImpl(database)
    }

    @Provides
    @Singleton
    fun providesMrshlDatabase(context: Context): MrshlDatabase {
        return MrshlDatabase(driver = DatabaseDriverFactory(context).create())
    }

    @Provides
    @Singleton
    fun providesBaseApplication(): Context {
        return BaseApplication.INSTANCE
    }

    @Provides
    @Singleton
    fun providesAnswersApi(client: HttpClient): AnswersApiKtorImpl {
        return AnswersApiKtorImpl(client)
    }

    @Provides
    @Singleton
    fun providesAnswersHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }
}