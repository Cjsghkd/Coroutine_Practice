package com.example.coroutine_practice.ch_1_2

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun doOneTwoThree4() = coroutineScope {
    val job = launch { // 코루틴 빌더 launch는 Job 객체를 반환하며 이를 통해 종료될 때까지 기다릴 수 있습니다.
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L) // suspension point
        println("3!")
    }
    job.join() // suspension point

    launch {
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }
    launch {
        println("launch3: ${Thread.currentThread().name}")
        delay(500L) // suspension point
        println("2!")
    }
    println("4!")
}

fun main() = runBlocking {
    doOneTwoThree4()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}