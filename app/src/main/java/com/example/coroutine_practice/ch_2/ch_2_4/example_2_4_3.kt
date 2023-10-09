package com.example.coroutine_practice.ch_2.ch_2_4

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().conflate()
            .collect { value ->
                delay(300)
                println(value)
            }
    }
    println("Collected in $time ms")
}

// conflate는 처리보다 빨리 발생한 데이터의 중간 값들을 누락한다.