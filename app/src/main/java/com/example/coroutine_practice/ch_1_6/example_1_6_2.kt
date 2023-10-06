package com.example.coroutine_practice.ch_1_6

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun printRandom2() {
    delay(500L)
    println(Random.nextInt(0, 500))
}

fun main() {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch(Dispatchers.IO) {
        launch { printRandom2() }
    }
    Thread.sleep(1000L)
}

// CoroutineScope는 인자로 CoroutineContext를 받는데 코루틴 엘리먼트를 하나만 넣어도 되고, 여러개 합쳐 넣어도 된다.
// 하나의 코루틴 엘리먼트, 디스페처 Dispatchers.Default만 넣어도 코루틴 컨텍스트가 만들어진다.