package com.example.coroutine_practice.ch_2.ch_2_4

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().collect { value ->
            delay(300)
            println(value)
        }
    }
    println("Collected in $time ms")
}

// 버퍼가 없다면 현재 코드 기준 한번 실행당 400ms 정도의 손해를 봐서 최소 1200ms 이상 걸린다