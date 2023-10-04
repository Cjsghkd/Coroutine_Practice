package com.example.coroutine_practice.ch_1_2

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun doOneTwoThree() {
//    launch { // this : 코루틴. Receiver
//        println("launch1: ${Thread.currentThread().name}")
//        delay(1000L) // suspension point
//        println("3!")
//    }
//    launch { // this : 코루틴. Receiver
//        println("launch2: ${Thread.currentThread().name}")
//        delay(1000L) // suspension point
//        println("1!")
//    }
//    launch { // this : 코루틴. Receiver
//        println("launch3: ${Thread.currentThread().name}")
//        delay(500L) // suspension point
//        println("2!")
//    }
    println("4!")
}

fun main() = runBlocking {
    doOneTwoThree() // suspension point
    println("runBlocking: ${Thread.currentThread().name}")
    delay(100L) // suspension point
    println("5!")
    doOneTwoThree() // suspension point
}

// Unresolved reference: launch
// Suspension functions can be called only within coroutine body
// 코루틴 빌더는 코루틴 스코프 내에서만 호출해야 한다.