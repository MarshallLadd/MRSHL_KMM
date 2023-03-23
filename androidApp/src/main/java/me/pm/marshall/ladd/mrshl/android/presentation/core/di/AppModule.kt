package me.pm.marshall.ladd.mrshl.android.presentation.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import me.pm.marshall.ladd.mrshl.android.BaseApplication
import me.pm.marshall.ladd.mrshl.core.database.DatabaseDriverFactory
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.database.sqlDelight.PuzzleDatabaseSqlDelightImpl
import me.pm.marshall.ladd.mrshl.core.network.HttpClientFactory
import me.pm.marshall.ladd.mrshl.core.network.answers.PuzzlesApiInterface
import me.pm.marshall.ladd.mrshl.core.network.answers.PuzzlesApiKtorImpl
import me.pm.marshall.ladd.mrshl.database.MrshlDatabase
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemote
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBaseApplicationContext(): Context {
        return BaseApplication.INSTANCE
    }

    @Provides
    @Singleton
    fun providesDatabaseOperations(database: MrshlDatabase): PuzzleDatabaseOperations {
        return PuzzleDatabaseSqlDelightImpl(database)
    }

    @Provides
    @Singleton
    fun providesMrshlDatabase(context: Context): MrshlDatabase {
        return MrshlDatabase(driver = DatabaseDriverFactory(context).create())
    }

    @Provides
    @Singleton
    fun providesAnswersApi(client: HttpClient): PuzzlesApiInterface {
        return PuzzlesApiKtorImpl(client)
    }

    @Provides
    @Singleton
    fun providesAnswersHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun providesCachePuzzlesFromRemoteUseCase(
        answersApiKtorImpl: PuzzlesApiInterface,
        database: PuzzleDatabaseOperations,
    ): CachePuzzlesFromRemote {
        return CachePuzzlesFromRemote(
            answersApiKtorImpl,
            database,
        )
    }
}
