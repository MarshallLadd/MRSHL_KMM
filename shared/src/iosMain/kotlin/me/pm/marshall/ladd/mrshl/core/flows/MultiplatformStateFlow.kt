package me.pm.marshall.ladd.mrshl.core.flows

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

actual open class MultiplatformStateFlow<T> actual constructor(
    private val flow: StateFlow<T>,
) : MultiplatformFlow<T>(flow), StateFlow<T> {
    override val replayCache: List<T>
        get() = flow.replayCache
    override val value: T
        get() = flow.value

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        flow.collect(collector)
    }
}
