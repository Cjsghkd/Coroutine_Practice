package com.example.coroutine_practice.ch_3.ch_3_3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun produceNumbers(channel: SendChannel<Int>, from: Int, interval: Long) {
    var x = from
    while (true) {
        channel.send(x)
        x += 2
        delay(interval)
    }
}

fun CoroutineScope.processSimpleNumber(channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("${it}을 받았습니다.")
    }
}

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>() // Channel = Receive Channel + Send Channel
    launch {
        produceNumbers(channel, 1, 100L) // 1, 3, 5, 7, 9 ... (한번 보낼 때 마다 100ms)
    }
    launch {
        produceNumbers(channel, 2, 150L) // 2, 4, 6, 8, 10 ... (한번 보낼 때 마다 150ms)
    } // 생산자 2, 소비자 1 (2개의 생산자가 하나의 채널을 효율적으로 사용)
    processSimpleNumber(channel)
    delay(1000L)
    coroutineContext.cancelChildren()
}

// 팬인은 팬아웃의 반대의 경우로, 생산자가 많은 것이다.