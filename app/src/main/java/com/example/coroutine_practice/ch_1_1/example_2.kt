package com.example.coroutine_practice.ch_1_1

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println(this)
    println(Thread.currentThread().name)
    println("Hello")
}

// BlockingCoroutine은 CoroutineScope의 자식입니다.
// 코틀린 코루틴을 쓰는 모든 곳에는 코루틴 스코프(CoroutineScope)가 있다고 생각하면 됩니다.
// 코루틴의 시작은 코루틴 스코프다!