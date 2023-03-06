package me.pm.marshall.ladd.mrshl.core.flows

import kotlinx.coroutines.flow.StateFlow

expect class MultiplatformStateFlow<T>(flow: StateFlow<T>) : StateFlow<T>

fun <T> StateFlow<T>.toMultiplatformStateFlow() = MultiplatformStateFlow(this)
