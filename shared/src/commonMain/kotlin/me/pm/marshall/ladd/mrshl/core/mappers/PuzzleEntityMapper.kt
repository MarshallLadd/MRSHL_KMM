package me.pm.marshall.ladd.mrshl.core.mappers

import database.PuzzleEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import me.pm.marshall.ladd.mrshl.core.network.answers.model.AllPuzzlesNetworkDTO
import me.pm.marshall.ladd.mrshl.presentation.core.PuzzleForPlay
import me.pm.marshall.ladd.mrshl.presentation.core.TileState
import me.pm.marshall.ladd.mrshl.presentation.core.toInt
import me.pm.marshall.ladd.mrshl.presentation.core.toTileState
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.UIPuzzleHistoryItem

// PuzzleEntity is the sqlDelight generated class for the DB

fun PuzzleEntity.toPuzzleForPlay(): PuzzleForPlay {

    return PuzzleForPlay(
        id = this.id,
        answer = this.answer,
        guessList = this.guessString
            ?.toCharArray()
            ?.mapIndexed { index, char ->
                this.tileStatusString
                    ?.toCharArray()
                    ?.map { it.digitToInt() }
                    ?.getOrElse(index = index, defaultValue = { 0 })
                    ?.toTileState(char) ?: TileState.UnsubmittedGuess(null)
            } ?: emptyList(),
        puzzleDateString = Instant
            .fromEpochMilliseconds(this.puzzleDate)
            .toLocalDateTime(TimeZone.UTC)
            .toString(),
        completedDateString = this.completedDate?.let {
            Instant
                .fromEpochMilliseconds(this.completedDate)
                .toLocalDateTime(TimeZone.UTC)
                .toString()
        },
        numberOfGuesses = this.numberOfGuesses ?: 0
    )
}

fun PuzzleForPlay.toPuzzleDbEntity(): PuzzleEntity {
    var guessString = ""
    if (guessList.isNotEmpty()) {
        guessList.forEach { singleGuess ->
            guessString = "$guessString${singleGuess.letter}"
        }
    }
    var tileStateString = ""
    if (guessList.isNotEmpty()) {
        guessList.forEach { singleGuess ->
            tileStateString = "$tileStateString${singleGuess.toInt()}"
        }
    }
    val puzzleDate = if (this.puzzleDateString.isNotEmpty()) {
        this.puzzleDateString
            .toLocalDateTime()
            .toInstant(TimeZone.UTC)
            .toEpochMilliseconds()
    } else 0
    return PuzzleEntity(
        id = this.id,
        answer = this.answer,
        guessString = guessString,
        tileStatusString = null,
        puzzleDate = puzzleDate,
        completedDate = this.completedDateString
            ?.toLocalDateTime()
            ?.toInstant(TimeZone.UTC)
            ?.toEpochMilliseconds(),
        numberOfGuesses = this.numberOfGuesses
    )
}

fun AllPuzzlesNetworkDTO.toPuzzleDbEntity(): PuzzleEntity {
    return PuzzleEntity(
        id = this.id.toLong(),
        answer = this.answer,
        puzzleDate = this.day.toLong(),
        guessString = null,
        tileStatusString = null,
        completedDate = null,
        numberOfGuesses = 0
    )
}

fun PuzzleEntity.toUIPuzzleHistoryEntity(): UIPuzzleHistoryItem {
    return UIPuzzleHistoryItem(
        id = this.id.toInt(),
        puzzleDateString = Instant
            .fromEpochMilliseconds(this.puzzleDate)
            .toLocalDateTime(TimeZone.UTC)
            .date
            .toString(),
        completedDateString = this.completedDate
            ?.let {
                Instant
                    .fromEpochMilliseconds(this.completedDate)
                    .toLocalDateTime(TimeZone.UTC)
                    .date
                    .toString()
            },
        tileStateList = this.tileStatusString
            ?.toCharArray()
            ?.map {
                it
                    .digitToInt()
                    .toTileState(null)
            } ?: emptyList()
    )
}

fun PuzzleForPlay.toUIPuzzleHistoryEntity(): UIPuzzleHistoryItem {
    return this.toPuzzleDbEntity().toUIPuzzleHistoryEntity()
}
