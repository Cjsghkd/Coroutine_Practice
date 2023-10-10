package com.example.coroutine_practice.ch_2.ch_2_7

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun simple1(): Flow<String> =
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i)
        }
    }.map { value ->
        check(value <= 1) { "Crashed on $value" }
        "string $value"
    }

fun main() = runBlocking {
    try {
        simple1().collect { value -> println(value) }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}
