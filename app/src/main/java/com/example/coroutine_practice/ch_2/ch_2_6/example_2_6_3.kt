package com.example.coroutine_practice.ch_2.ch_2_6

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapLatest {
            requestFlow(it)
        }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

// flatMapLatest는 다음 요소의 플레트닝을 시작하며 이전에 진행 중이던 플레트닝을 취소한다. (마지막 플레트닝만 실행한다)