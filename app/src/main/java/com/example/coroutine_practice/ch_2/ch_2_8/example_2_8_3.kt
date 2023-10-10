package com.example.coroutine_practice.ch_2.ch_2_8

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

fun simple2(): Flow<Int> = flow {
    emit(1)
    emit(2)
    emit(3)
}

fun main() = runBlocking {
    simple2()
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completion exceptionally")
            } else {
                println("Flow completed")
            }
        }
        .catch { cause -> println("Caught exception") }
        .collect { value -> println(value) }
}

// onCompletion은 종료 처리를 할 때 예외가 발생되었는지 여부를 알 수 있다.