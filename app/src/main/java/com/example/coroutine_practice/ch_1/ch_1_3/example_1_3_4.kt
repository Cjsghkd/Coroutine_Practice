package com.example.coroutine_practice.ch_1.ch_1_3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun doCount3() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L

        while (i <= 10 && isActive) { // isActive를 호출하면 해당 코루틴이 여전히 활성화된지 확인할 수 있다.
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }

    delay(200L)
    job1.cancelAndJoin()
    println("doCount Done!")
}

fun main() = runBlocking {
    com.example.coroutine_practice.ch_1.ch_1_3.doCount3()
}

// isActive를 사용해서 cancel 가능한 code를 만들 수 있다.