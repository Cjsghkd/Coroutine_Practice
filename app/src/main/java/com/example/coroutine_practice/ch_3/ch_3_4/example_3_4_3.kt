package com.example.coroutine_practice.ch_3.ch_3_4

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>(2, BufferOverflow.DROP_OLDEST)
    launch {
        for (x in 1..50) {
            channel.send(x) // 받든 안받든 채널로 계속 보낸다.
        }
        channel.close()
    }

    delay(500L)

    for (x in channel) {
        println("$x 수신")
        delay(100L)
    }
    println("완료")
}

// 버퍼의 오버플로우 정책에 따라 다른 결과가 나올 수 있다.
// SUSPEND - 잠이 들었다 깨어난다.
// DROP_OLDEST - 예전 데이터를 지운다.
// DROP_LATEST - 새 데이터를 지운다.