package com.example.projectbasesetup.utils

import java.util.concurrent.atomic.AtomicBoolean

open class Event<out T>(private val content: T) {

     var hasBeenHandled = AtomicBoolean(false)
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(handleContent: (T) -> Unit) {
        if (!hasBeenHandled.get()) {
            handleContent(content)
            hasBeenHandled.set(true)
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}