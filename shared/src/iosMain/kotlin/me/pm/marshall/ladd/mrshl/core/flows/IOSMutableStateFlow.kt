package me.pm.marshall.ladd.mrshl.core.flows

import kotlinx.coroutines.flow.MutableStateFlow

class IOSMutableStateFlow<T>(
    initialValue: T,
) : MultiplatformMutableStateFlow<T>(MutableStateFlow(initialValue))
