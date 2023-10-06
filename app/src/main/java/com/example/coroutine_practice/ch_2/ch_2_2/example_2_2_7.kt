package com.example.coroutine_practice.ch_2.ch_2_2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

suspend fun someCalc3(i: Int): Int {
    delay(10L)
    return i * 2
}

fun main() = runBlocking<Unit> {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc3(it))
    }.drop(5).collect {
        println(it)
    }
}

//fun main() = runBlocking<Unit> {
//    (1..20).asFlow().transform {
//        emit(it)
//        emit(someCalc3(it))
//    }.dropWhile {
//        it < 5
//    }.collect {
//        println(it)
//    }
//}

// drop 연산자는 처음 몇개의 결과를 버립니다.
// 또한 take가 takeWhile를 가지듯 dropWhile도 있습니다.