package me.pm.marshall.ladd.mrshl.core.flows

import kotlinx.coroutines.flow.Flow

expect class MultiplatformFlow<T>(flow: Flow<T>) : Flow<T>

fun <T> Flow<T>.toMultiplatformFlow() = MultiplatformFlow(this)
