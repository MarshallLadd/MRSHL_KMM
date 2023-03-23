package me.pm.marshall.ladd.mrshl.core.mappers

import database.PuzzleEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import me.pm.marshall.ladd.mrshl.core.network.answers.model.AllPuzzlesNetworkDTO
import me.pm.marshall.ladd.mrshl.presentation.core.PuzzleForPlay
import me.pm.marshall.ladd.mrshl.presentation.core.TileState
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.UIPuzzleHistoryItem

// PuzzleEntity is the sqlDelight generated class for the DB

fun PuzzleEntity.toPuzzleForPlay(): PuzzleForPlay {
    return PuzzleForPlay(
        id = this.id,
        answer = this.answer,
        guessList = this.guessString
            ?.split(',')
            ?.map {
                it.first()
            }?.map {
                TileState.UnsubmittedGuess(it)
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
    )
}

fun PuzzleForPlay.toPuzzleDbEntity(): PuzzleEntity {
    var guessString = ""
    if (guessList.isNotEmpty()) {
        guessList.forEachIndexed { index, singleGuess ->
            guessString = if (index != guessList.lastIndex) {
                "$guessString$singleGuess,"
            } else {
                "$guessString$singleGuess"
            }
        }
    }
    return PuzzleEntity(
        id = this.id,
        answer = this.answer,
        guessString = guessString,
        puzzleDate = puzzleDateString
            .toLocalDateTime()
            .toInstant(TimeZone.UTC)
            .toEpochMilliseconds(),
        completedDate = completedDateString
            ?.toLocalDateTime()
            ?.toInstant(TimeZone.UTC)
            ?.toEpochMilliseconds(),
    )
}

fun AllPuzzlesNetworkDTO.toPuzzleDbEntity(): PuzzleEntity {
    return PuzzleEntity(
        id = this.id.toLong(),
        answer = this.answer,
        puzzleDate = this.day.toLong(),
        guessString = null,
        completedDate = null,
    )
}

fun PuzzleEntity.toUIPuzzleHistoryEntity(): UIPuzzleHistoryItem {
    return UIPuzzleHistoryItem(
        id = this.id.toInt(),
//        guessList = this.guessString?.split(',') ?: emptyList(),
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
    )
}

fun PuzzleForPlay.toUIPuzzleHistoryEntity(): UIPuzzleHistoryItem {
    return this.toPuzzleDbEntity().toUIPuzzleHistoryEntity()
}
