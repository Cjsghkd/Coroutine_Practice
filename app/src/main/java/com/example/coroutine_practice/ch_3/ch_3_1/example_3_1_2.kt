package com.example.coroutine_practice.ch_3.ch_3_1

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch {
        for (x in 1..10) {
            channel.send(x)
        }
        repeat(10) {
            println(channel.receive())
        }
        println("완료")
    }
}

// send나 receive가 suspension point 이고, 서로에게 의존적이기 때문에 같은 코루틴에서 사용하는 것은 위험할 수 있다.
// 현재 이 코드는 무한 로딩이 걸린다.