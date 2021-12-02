package com.jetdev.adventofcode.utils


class Event<T>(private val content: T) {
    private var hasBeenHandled = false

    val contentIfNotHandled: T?
        get() = if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }

    fun hasBeenHandled(): Boolean {
        return hasBeenHandled
    }

}