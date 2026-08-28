package com.heb.centralmarket.uicart.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember

/**
 * Returns a debounced onClick lambda that will only invoke the provided [onClick]
 * when the last invocation occurred more than [debounceDelayMillis] ago.
 *
 * The returned lambda runs synchronously on the calling thread (no coroutine launch)
 * and updates the last-invoke timestamp atomically in the composition scope.
 */
@Composable
fun rememberDebouncedClick(
    onClick: (() -> Unit)?,
    debounceDelayMillis: Long = 500L,
): (() -> Unit)? {
    val lastInvoke = remember { mutableLongStateOf(0L) }
    return remember(onClick, debounceDelayMillis) {
        if (onClick == null) null else {
            {
                val now = System.currentTimeMillis()
                if (now - lastInvoke.longValue >= debounceDelayMillis) {
                    lastInvoke.longValue = now
                    onClick()
                }
            }
        }
    }
}

/**
 * Generic debounced callback for parameterized click-like handlers.
 */
@Composable
fun <T> rememberDebouncedCallback(
    onClick: ((T) -> Unit)?,
    debounceDelayMillis: Long = 500L,
): ((T) -> Unit)? {
    val lastInvoke = remember { mutableLongStateOf(0L) }
    return remember(onClick, debounceDelayMillis) {
        if (onClick == null) null else {
            { t: T ->
                val now = System.currentTimeMillis()
                if (now - lastInvoke.longValue >= debounceDelayMillis) {
                    lastInvoke.longValue = now
                    onClick(t)
                }
            }
        }
    }
}


