package me.pm.marshall.ladd.mrshl.core.flows

import kotlinx.coroutines.flow.Flow

actual class MultiplatformFlow<T> actual constructor(
    private val flow: Flow<T>,
) : Flow<T> by flow
