package me.pm.marshall.ladd.mrshl.android.presentation.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.pm.marshall.ladd.mrshl.android.presentation.loadingScreen.AndroidLoadingScreenViewModel
import me.pm.marshall.ladd.mrshl.core.database.PuzzleDatabaseOperations
import me.pm.marshall.ladd.mrshl.core.database.sqlDelight.PuzzleDatabaseSqlDelightImpl
import me.pm.marshall.ladd.mrshl.core.network.answers.AnswersApiKtorImpl
import me.pm.marshall.ladd.mrshl.domain.useCases.CachePuzzlesFromRemote
import me.pm.marshall.ladd.mrshl.presentation.loadingScreen.LoadingScreenViewModel

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesLoadingScreenViewModel(
        cachePuzzlesFromRemote: CachePuzzlesFromRemote
    ): AndroidLoadingScreenViewModel {
        return AndroidLoadingScreenViewModel(cachePuzzlesFromRemote)
    }

    @Provides
    @ViewModelScoped
    fun providesCachePuzzlesFromRemoteUseCase(
        answersApiKtorImpl: AnswersApiKtorImpl,
        database: PuzzleDatabaseSqlDelightImpl
    ): CachePuzzlesFromRemote {
        return CachePuzzlesFromRemote(
            answersApiKtorImpl,
            database
        )
    }

}