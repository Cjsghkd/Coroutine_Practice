package com.example.coroutine_practice.ch_3.ch_3_2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.filterOdd(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {// 샌드 채널 (ProduceScope = CoroutineScope + SendScope)
    for (i in numbers) {
        if (i % 2 == 1) { // 홀수 필터 ( 1!, 3!, 5! ...
            send("${i}!")
        }
    }
}

fun main() = runBlocking {
    val numbers = produceNumbers() // 리시브 채널, send X
    val oddNumbers = filterOdd(numbers) // 리시브 채널, send X

    repeat(10) {
        println(oddNumbers.receive())
    }
    println("완료")
    coroutineContext.cancelChildren()
}
