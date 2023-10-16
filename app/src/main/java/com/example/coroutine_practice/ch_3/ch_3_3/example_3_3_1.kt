package com.example.coroutine_practice.ch_3.ch_3_3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceDelayNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.processNumber(id: Int, channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("${id}가 ${it}을 받았습니다.")
    }
}

fun main() = runBlocking {
    val producer = produceDelayNumbers()
    repeat(5) {// 5개의 코루틴 생성
        processNumber(it, producer)
    }
    delay(1000L)
    producer.cancel()
}

// 팬아웃은 여러 코루틴이 동시에 채널을 구독하는 것이다.