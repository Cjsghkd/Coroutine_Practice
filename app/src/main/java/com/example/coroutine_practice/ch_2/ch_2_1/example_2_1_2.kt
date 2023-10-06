package com.example.coroutine_practice.ch_2.ch_2_1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.random.Random

fun flowSomething1(): Flow<Int> = flow {
    repeat(10) {
        emit(Random.nextInt(0, 500)) // Int 값을 방출 (위에서 Flow<Int> 타입을 방출해야 해서)
        delay(100L)
    }
}

fun main() = runBlocking<Unit> {
    val result = withTimeoutOrNull(500L) {
        flowSomething1().collect { value ->
            println(value)
        }
        true
    } ?: false
    if (!result) {
        println("취소되었습니다.")
    }
}

// withTimeoutOrNull을 이용햐 취소할 수 있다.

