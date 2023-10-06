package com.example.coroutine_practice.ch_2_1

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    listOf(1, 2, 3, 4, 5).asFlow().collect { value ->
        println(value)
    }
    (6..10).asFlow().collect {
        println(it)
    }

    // 다 똑같이 출력된다.
//    flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect { value ->
//        println(value)
//    }
//    flow {
//        emit(1)
//        emit(2)
//        emit(3)
//        emit(4)
//        emit(5)
//        emit(6)
//        emit(7)
//        emit(8)
//        emit(9)
//        emit(10)
//    }.collect { println(it) }
}

// asFlow는 컬렉션이나 시퀀스를 전달해 플로우를 만들 수 있다.

