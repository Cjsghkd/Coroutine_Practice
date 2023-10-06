package com.example.coroutine_practice.ch_1_6

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun printRandom() {
    delay(500L)
    println(Random.nextInt(0, 500))
}

fun main() {
    val job = GlobalScope.launch(Dispatchers.IO) {
        launch { printRandom() }
    }
    Thread.sleep(1000L) // Thread.sleep을 쓴 이유는 main이 runBlocking이 아니여서 delay를 수행할 수 없기 때문이다.
}

// 어디에도 속하지 않지만 원래부터 존재하는 전역 GlobalScope를 이용하면 코루틴을 쉽게 수행할 수 있다.
// 하지만 어떤 계층에도 속하지 않고 영원히 동작하게 된다는 문제점이 있다.
// 일반적으로 GlobalScope을 주로 사용하지 않는다.