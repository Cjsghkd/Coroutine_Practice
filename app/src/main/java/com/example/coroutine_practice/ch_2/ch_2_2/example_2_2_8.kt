package com.example.coroutine_practice.ch_2.ch_2_2

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val value = (1..10)
        .asFlow() // 1 ~ 10
        .reduce { a, b -> // (a = 1, b = 2) -> (a = 3, b = 3) -> (a = 6, b = 4)
            a + b
        } // 3 -> 6 -> 10
    println(value)
}

// collect, reduce, fold, toList, toSet과 같은 연산자는 플로우를 끝내는 함수라 종단 연산자(terminal operator)라고 합니다.
// reduce는 첫번째 값을 결과에 넣은 후 각 값을 가져와 누진적으로 계산합니다.
// 1 ~ 10 을 차례대로 더하는 느낌