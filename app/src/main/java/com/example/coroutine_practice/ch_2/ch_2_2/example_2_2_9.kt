package com.example.coroutine_practice.ch_2.ch_2_2

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val value = (1..10)
        .asFlow()
        .fold(10) { a, b -> // (a = 10, b = 1) -> (a = 11, b = 2) -> (a = 13, b = 3)
            a + b
        } // 11 -> 13 -> 16
    println(value)
}

// fold 연산자는 reduce와 매우 유사하지만 초기값이 있습니다.