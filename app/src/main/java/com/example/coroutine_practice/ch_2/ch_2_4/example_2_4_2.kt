package com.example.coroutine_practice.ch_2.ch_2_4

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().buffer()
            .collect { value ->
                delay(300) // 이 300ms 대기를 기다리지 않고 바로 다음 데이터를 불러온다.
                println(value)
            }
    }
    println("Collected in $time ms")
}

// buffer를 추가해 보내는 측이 더 이상 기다리지 않게 한다.