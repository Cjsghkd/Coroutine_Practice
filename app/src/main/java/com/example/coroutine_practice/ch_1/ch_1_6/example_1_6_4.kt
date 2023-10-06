package com.example.coroutine_practice.ch_1.ch_1_6

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

suspend fun printRandom5() {
    delay(1000L)
    println(Random.nextInt(0, 500))
}

suspend fun printRandom6() {
    delay(500L)
    throw ArithmeticException()
}

val ceh2 = CoroutineExceptionHandler { _, exception ->
    println("Something happend: $exception")
}

fun main() = runBlocking<Unit> { // 1 최상단 코루틴
    val job = launch(ceh2) {// 2
        val a = async { printRandom5() } // 3
        val b = async { printRandom6() } // 3
        println(a.await())
        println(b.await())
    }
    job.join()
}

// runBlocking에서는 CEH를 사용할 수 없다.
// runBlocking은 자식이 예외로 종료되면 항상 종료되고, CEH를 호출하지 않는다.