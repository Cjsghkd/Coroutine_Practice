package com.example.coroutine_practice.ch_1_3

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun doOneTwoThree3() = coroutineScope { // withContext(NonCancellable)는 취소가 불가능합니다.
    val job1 = launch {
        withContext(NonCancellable) {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        }
        delay(1000L)
        println("job1: end")
    }

    val job2 = launch {
        withContext(NonCancellable) {
            println("launch2: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
        delay(1000L)
        println("job2: end")
    }

    val job3 = launch {
        withContext(NonCancellable) {
            println("launch3: ${Thread.currentThread().name}")
            delay(1000L)
            println("2!")
        }
        delay(1000L)
        println("job3: end")
    }

    delay(800L)
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}

fun main() = runBlocking {
    doOneTwoThree3()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}