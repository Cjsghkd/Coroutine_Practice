package com.example.coroutine_practice.ch_1.ch_1_3

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun doOneTwoThree() = coroutineScope { // 명시적인 Job에 대해 cancel 메서드를 호출해 취소할 수 있습니다.
    val job1 = launch {
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }

    val job2 = launch {
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }

    val job3 = launch {
        println("launch3: ${Thread.currentThread().name}")
        delay(500L)
        println("2!")
    }

    delay(800L)
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}

fun main() = runBlocking {
    com.example.coroutine_practice.ch_1.ch_1_3.doOneTwoThree()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}