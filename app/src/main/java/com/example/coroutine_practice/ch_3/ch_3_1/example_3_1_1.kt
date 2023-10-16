package com.example.coroutine_practice.ch_3.ch_3_1

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..10) {
            channel.send(x) // suspension point
        }
    }

    repeat(10) {
        println(channel.receive()) // suspension point
    }
    println("완료")
}

// 채널은 일종의 파이프이다.
// 송신측에서 채널에 send로 데이터를 전달하고, 수신 측에서 채널을 통해 receive 받는다.
// trySend와 tryReceive도 있고, 과거에는 null을 반환하는 offer와 poll이 있었다.
// 수신과 송신 모두 각각 보낼 데이터, 받을 데이터가 없으면 suspend 됬다가 생겼을 때 다시 실행된다.