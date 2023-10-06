package com.example.coroutine_practice.ch_1.ch_1_6

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

suspend fun printRandom3() {
    delay(1000L)
    println(Random.nextInt(0, 500))
}

suspend fun printRandom4() {
    delay(500L)
    throw ArithmeticException()
}

val ceh = CoroutineExceptionHandler { _, exception -> // _는 원래 CoroutineContext지만 사용을 안할 것이라면 _로 표시해도 무방하다.
    println("Something happend: $exception")
}

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch(ceh) {
        launch { printRandom3() }
        launch { printRandom4() }
    }
    job.join()
}

// 예외를 가장 체계적으로 다루는 방법은 CEH(Coroutine Exception Handler, 코루틴 익셉션 핸들러)를 사용하는 것이다.
// 일반적으로 CEH를 만들 때 첫번째 인자인 CoroutineContext는 사용을 안하는 경우가 많아서 주로 _로 표시한다.