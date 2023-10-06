package com.example.coroutine_practice.ch_1_6

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.random.Random

suspend fun printRandom9() {
    delay(1000L)
    println(Random.nextInt(0, 500))
}

suspend fun printRandom10() {
    delay(500L)
    throw ArithmeticException()
}

suspend fun supervisoredFunc() = supervisorScope {// 코루틴 스코프와 슈퍼바이저 잡을 합친듯 한 SupervisorScope가 있다.
    launch { printRandom9() }
    launch(ceh4) { printRandom10() }
    // supervisorScope는 에러가 나는 쪽에다가 ceh를 붙이거나 try catch를 써야한다.
    // 왜냐면 supervisorScope는 에러를 처리하지 않으면 외부로 에러가 전파되기 때문이다.
}

val ceh4 = CoroutineExceptionHandler { _, exception ->
    println("Something happend: $exception")
}

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch {
        supervisoredFunc()
    }
    job.join()
}