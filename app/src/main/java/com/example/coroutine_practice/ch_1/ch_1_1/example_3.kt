package com.example.coroutine_practice.ch_1.ch_1_1

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
//    println(coroutineContext)
    println(this.coroutineContext)
    println(Thread.currentThread().name)
    println("Hello")
}

// 코루틴 스코프는 코루틴을 제대로 처리하기 위한 정보, 코루틴 컨텍스트(CoroutineContext)를 가지고 있습니다.