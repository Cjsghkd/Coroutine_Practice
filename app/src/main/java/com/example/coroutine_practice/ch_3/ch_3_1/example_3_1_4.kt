package com.example.coroutine_practice.ch_3.ch_3_1

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val oneToTen = produce { // ProducerScope = CoroutineScope + SendChannel
        // this.send // this.coroutineContext
        for (x in 1..10) {
            channel.send(x)
        }
    }

    oneToTen.consumeEach {
        println(it)
    }
    println("완료")
}