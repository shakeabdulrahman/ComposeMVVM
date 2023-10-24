package com.example.composemvvm.common

internal interface MultipleEventsCutter {
    fun processEvent(event: () -> Unit)

    companion object
}

internal fun MultipleEventsCutter.Companion.get(time: Long = 600L): MultipleEventsCutter =
    MultipleEventsCutterImpl(time)

private class MultipleEventsCutterImpl(val time: Long) : MultipleEventsCutter {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= time) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}