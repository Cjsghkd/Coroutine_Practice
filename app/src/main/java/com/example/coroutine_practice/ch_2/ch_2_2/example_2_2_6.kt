package com.example.coroutine_practice.ch_2.ch_2_2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

suspend fun someCalc2(i: Int): Int {
    delay(10L)
    return i * 2
}

fun main() = runBlocking<Unit> {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc2(it))
    }.takeWhile {
        it < 15
//        it >= 15 (만약 이렇게 요청한다면 처음 값이 조건을 달성하지 못하기 때문에 바로 종료된다.)
    }.collect {
        println(it)
    }
}

// takeWhile을 이용해 조건을 만족하는 동안만 값을 가져오게 할 수 있습니다.