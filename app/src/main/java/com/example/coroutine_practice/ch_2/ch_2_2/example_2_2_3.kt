package com.example.coroutine_practice.ch_2.ch_2_2

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    (1..20).asFlow().filterNot {
        (it % 2) == 0
    }.collect {
        println(it)
    }
}

//fun main() = runBlocking<Unit> {// 이런식으로 오퍼레이터(연산자)를 연속으로 쓸 수도 있다.
//    (1..20).asFlow().filterNot {
//        (it % 2) == 0
//    }.map {
//        it * 3
//    }.collect {
//        println(it)
//    }
//}