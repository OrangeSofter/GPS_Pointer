package ru.upsoft.gpspointer.presentation.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> LifecycleOwner.observeWhenResumed(flow: Flow<T>, action: suspend (data: T) -> Unit) {
    lifecycle.coroutineScope.launchWhenResumed {
        flow.collect {
            action(it)
        }
    }
}

fun <T> LifecycleOwner.observeWhenStarted(flow: Flow<T>, action: suspend (data: T) -> Unit) {
    lifecycle.coroutineScope.launchWhenStarted {
        flow.collect {
            action(it)
        }
    }
}