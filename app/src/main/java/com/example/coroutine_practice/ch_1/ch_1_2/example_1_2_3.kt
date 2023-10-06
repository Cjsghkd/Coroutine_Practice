package com.example.coroutine_practice.ch_1.ch_1_2

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun doOneTwoThree3() = coroutineScope { // 10만개의 간단한 일을 하는 코루틴도 큰 부담은 아니지만 코틀린 플레이 그라운드의 한계로 그렇게 많은 코루틴은 로그를 못 찍는다.
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
    repeat(10_000) {
        launch {
//            println("launch3: ${Thread.currentThread().name}")
            delay(500L) // suspension point
//            println("2!")
        }
    }
    println("4!")
}

fun main() = runBlocking {
    com.example.coroutine_practice.ch_1.ch_1_2.doOneTwoThree3()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}