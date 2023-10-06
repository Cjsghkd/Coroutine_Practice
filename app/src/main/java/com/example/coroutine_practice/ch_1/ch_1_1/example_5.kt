package com.example.coroutine_practice.ch_1.ch_1_1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        println("launch: ${Thread.currentThread().name}")
        println("World!")
    }
    println("runBlocking: ${Thread.currentThread().name}")
    delay(500L)
    println("Hello")
}

// delay 함수의 인자로 밀리세컨드 단위의 시간을 지정하여 Hello를 조금 더 늦게 수행할 수 있다.