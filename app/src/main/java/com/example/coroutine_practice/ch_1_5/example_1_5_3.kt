package com.example.coroutine_practice.ch_1_5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    async(Dispatchers.Unconfined) {
        println("Unconfined / ${Thread.currentThread().name}")
        delay(100L) // suspension point
        println("Unconfined / ${Thread.currentThread().name}")
        delay(100L) // suspension point
        println("Unconfined / ${Thread.currentThread().name}")
    }
}

// Confined는 처음에는 부모의 스레드에서 수행되지만, 중단점(suspension point)에 오면 바뀌게 된다.
// Confined는 중단점 이후 어느 디스패처에서 수행될지 예측하기 어렵다.
// 가능하면 학실한 디스패처를 사용하는게 좋고, Unconfined는 왠만해서는 안 쓰는게 좋다.