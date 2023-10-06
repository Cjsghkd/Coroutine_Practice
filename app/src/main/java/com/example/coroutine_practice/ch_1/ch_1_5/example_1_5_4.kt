package com.example.coroutine_practice.ch_1.ch_1_5

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> { // 증부모
    val job = launch {// 부모
        launch(Job()) {// 코루틴 컨텍스트의 Job을 생성하게 되면 부모, 형제 관계가 아니고, 별도의 존재다.
            println(coroutineContext[Job])
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        }

        launch {
            println(coroutineContext[Job])
            println("launch2: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
    }

    delay(500L)
    job.cancelAndJoin()
    delay(1000L) // 만약 delay를 하지 않는다면 3!을 출력하지 못한 상태지만 다른 Job으로 구분이 되어서 종료가 됩니다.
}

