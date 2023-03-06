package me.pm.marshall.ladd.mrshl.core.flows

import kotlinx.coroutines.flow.MutableStateFlow

actual class MultiplatformMutableStateFlow<T> actual constructor(
    private val flow: MutableStateFlow<T>,
) : MutableStateFlow<T> by flow
