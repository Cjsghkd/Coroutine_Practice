package com.example.coroutine_practice.ch_2.ch_2_6

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapConcat {
            requestFlow(it)
        }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

// 플로우에서는 3가지 유형의 flatMap을 지원하고 있다. (flatMapConcat, flatMapMerge, flatMapLatest)
// flatMapConcat는 첫번째 요소에 대해 플레트닝을 하고 나서 두번째 요소를 합니다. (순서대로 처리)