package com.example.coroutine_practice.ch_2.ch_2_2

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val value = (1..10)
        .asFlow()
        .count {// 종단 연산자 (특정 값, 컬렉션, 결과를 가지고 있다)
            (it % 2) == 0
        }
    println(value)
}

//fun main() = runBlocking<Unit> {
//    val value = (1..10)
//        .asFlow()
//        .filter {// 중간 연산자 (결과 X)
//            (it % 2) == 0
//        }
//        .collect()
//    println(value)
//}

// count 연산자는 조건(술어)을 만족하는 자료의 갯수를 셉니다.