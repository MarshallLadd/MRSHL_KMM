package me.pm.marshall.ladd.mrshl.core.mappers

import database.PuzzleEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import me.pm.marshall.ladd.mrshl.core.network.answers.model.AllPuzzlesNetworkDTO
import me.pm.marshall.ladd.mrshl.presentation.core.TileState
import me.pm.marshall.ladd.mrshl.presentation.core.toTileState
import me.pm.marshall.ladd.mrshl.presentation.puzzleHistory.model.UIPuzzleHistoryItem

// PuzzleEntity is the sqlDelight generated class for the DB

fun AllPuzzlesNetworkDTO.toPuzzleDbEntity(): PuzzleEntity {
    return PuzzleEntity(
        id = this.id.toLong(),
        answer = this.answer,
        puzzleDate = this.day.toLong(),
        guessString = null,
        tileStatusString = null,
        completedDate = null,
        numberOfGuesses = 0,
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
        tileStateList = this.getTileStateList(),
    )
}

fun PuzzleEntity.getTileStateList(): List<TileState> {
    return this.tileStatusString
        ?.mapIndexed { index, digit ->
            val letter = this.guessString?.getOrNull(index)

            digit
                .digitToInt()
                .toTileState(letter)
        } ?: emptyList()
}
