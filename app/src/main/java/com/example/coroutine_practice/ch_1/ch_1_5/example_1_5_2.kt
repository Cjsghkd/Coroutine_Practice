package com.example.coroutine_practice.ch_1.ch_1_5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    async {
        println("부모의 콘텍스트 / ${Thread.currentThread().name}")
    }
    async(Dispatchers.Default) {
        println("Default / ${Thread.currentThread().name}")
    }
    async(Dispatchers.IO) {
        println("IO / ${Thread.currentThread().name}")
    }
    async(Dispatchers.Unconfined) {
        println("Unconfined / ${Thread.currentThread().name}")
        delay(100L)
        println("Unconfined / ${Thread.currentThread().name}")
    }
    async(newSingleThreadContext("Fast Campus")) {
        println("newSingleThreadContext / ${Thread.currentThread().name}")
    }
}

// launch외에도 async, withContext등의 코루틴 빌더에도 디스패처를 사용할 수 있다.