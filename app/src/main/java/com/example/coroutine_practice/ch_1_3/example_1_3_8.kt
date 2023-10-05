package com.example.coroutine_practice.ch_1_3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

suspend fun doCount5() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L

        while (i <= 10 && isActive) {
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }
}

fun main() = runBlocking {
    val result = withTimeoutOrNull(500L) { // withTimeoutOrNull을 이용해서 타임 아웃할 때 null을 반환하게 할 수 있다.
        doCount5()
        true
    } ?: false // 엘리스 연산자를 이용해서 null을 다른걸로 바꿔줄 수 있다.
    println(result)
}