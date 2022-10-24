package com.poc.ktor

object LivePageCount {
    private var count = 0

    fun incrementCount() {
        count++
    }

    fun decrementCount() {
        if (count == 0) return
        count--
    }

    fun getLiveCount() = count
}