package com.example.coroutine_practice.ch_1_4

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

suspend fun getRandom5(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

suspend fun getRandom6(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val value1 = async(start = CoroutineStart.LAZY) { getRandom5() } // LAZY 이기 때문
        val value2 = async(start = CoroutineStart.LAZY) { getRandom6() }

        value1.start() // 큐에 수행 예약을 한다
        value2.start()

        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    }
    println(elapsedTime)
}

// 원래 async 키워드를 사용하는 순간 코드 블록이 수행을 준비하지만 async(start = CoroutineStart.LAZY)로 인자를 전달하면
// 자기가 원하는 순간 수행을 준비하게 할 수 있고, 이 후 start 메서드를 이용해 수행을 준비하게 할 수 있다.