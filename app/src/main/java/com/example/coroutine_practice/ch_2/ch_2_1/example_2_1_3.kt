package com.example.coroutine_practice.ch_2.ch_2_1

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    flowOf(1, 2, 3, 4, 5).collect { value ->
        println(value)
    }
//    flow { // 이 코드도 위에 코드와 똑같다.
//        emit(1)
//        emit(2)
//        emit(3)
//        emit(4)
//        emit(5)
//    }.collect { println(it) }
}

// flowOf는 여러 값을 인자로 전달해 플로우를 만든다.

