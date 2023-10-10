package com.example.coroutine_practice.ch_2.ch_2_5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(100L) }
    val strs = flowOf("일", "이", "삼").onEach { delay(200L) }
    nums.combine(strs) { a, b -> "${a}은(는) $b" }
        .collect { println(it) }
}

// combine은 양쪽의 데이터를 같은 시점에 묶지 않고 한 쪽이 갱신되면 새로 묶어 데이지를 만듭니다.