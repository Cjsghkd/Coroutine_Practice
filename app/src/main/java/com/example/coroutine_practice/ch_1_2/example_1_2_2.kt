package com.example.coroutine_practice.ch_1_2

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun doOneTwoThree2() = coroutineScope {// this : 코루틴
    launch { // this : 코루틴
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }
    launch { // this : 코루틴
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }
    launch { // this : 코루틴
        println("launch3: ${Thread.currentThread().name}")
        delay(500L)
        println("2!")
    }
    println("4!")
}

fun main() = runBlocking {
    doOneTwoThree2()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}

// 코루틴 스코프를 만드는 방법은 스코프 빌더를 이용한다.
// runBlocking은 현재 쓰레드를 멈추게 만들고, 기다리지만 coroutineScope는 현재 쓰레드를 멈추게 하지 않고 호출한 쪽이 suspend되고 사용되면 다시 활동한다.