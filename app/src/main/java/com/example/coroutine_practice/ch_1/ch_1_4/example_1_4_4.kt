package com.example.coroutine_practice.ch_1.ch_1_4

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

suspend fun getRandom7(): Int {
    try {
        delay(1000L)
        return Random.nextInt(0, 500)
    } finally {
        println("getRandom7 is cancelled.")
    }
}

suspend fun getRandom8(): Int {
    delay(500L)
    throw IllegalStateException()
}

suspend fun doSomething() = coroutineScope {// 부모 코루틴 (문제가 발생하면 캔슬)
    val value1 = async {// 자식 코루틴 (문제가 발생하면 캔슬)
        com.example.coroutine_practice.ch_1.ch_1_4.getRandom7()
    }
    val value2 = async {// 자식 코루틴 (문제 발생)
        com.example.coroutine_practice.ch_1.ch_1_4.getRandom8()
    }
    try {
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    } finally {
        println("doSomething is cancelled.")
    }
}

fun main() = runBlocking {
    try {
        com.example.coroutine_practice.ch_1.ch_1_4.doSomething()
    } catch (e: IllegalStateException) {
        println("doSomething failed: $e")
    }
}

// 계층적으로 되어있는데 만약 부모 코루틴과 자식 코루틴 여러개가 문제가 없어도,
// 자식 코루틴 한 개만이라도 문제가 발생한다면 부모 코루틴과 나머지 자식 코루틴 모두 코루틴을 캔슬한다.