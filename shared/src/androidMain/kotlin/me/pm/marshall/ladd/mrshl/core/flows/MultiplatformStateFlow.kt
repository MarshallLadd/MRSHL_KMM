package me.pm.marshall.ladd.mrshl.core.flows

import kotlinx.coroutines.flow.StateFlow

actual class MultiplatformStateFlow<T> actual constructor(
    private val flow: StateFlow<T>,
) : StateFlow<T> by flow
