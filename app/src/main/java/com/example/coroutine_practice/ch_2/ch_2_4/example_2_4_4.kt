package com.example.coroutine_practice.ch_2.ch_2_4

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().collectLatest { value ->
            println("값 ${value}를 처리하기 시작합니다.")
            delay(300)
            println(value)
            println("처리를 완료했습니다.")
        }
    }
    println("Collected in $time ms")
}

// collectLatest은 마지막 값만 출력한다. (값을 받아 처리하던 중 새로운 값이 들어오면 취소해서 다시 처리한다)