package me.pm.marshall.ladd.mrshl.core.flows

import kotlinx.coroutines.flow.MutableStateFlow

expect class MultiplatformMutableStateFlow<T>(flow: MutableStateFlow<T>) : MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toMultiPlatformMutableStateFlow() = MultiplatformMutableStateFlow(this)
