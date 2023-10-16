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
        channel.close()
    }

//    channel.receive()

    for (x in channel) {
        println(x)
    }
    println("완료")
}

// 채널에서 더 이상 보낼 자료가 없으면 close 메서드를 이용해 채널을 닫을 수 있다.
// 채널은 for in을 이용해서 반복적으로 receive 할 수 있고, close 되면 for in은 자동으로 종료돤다.