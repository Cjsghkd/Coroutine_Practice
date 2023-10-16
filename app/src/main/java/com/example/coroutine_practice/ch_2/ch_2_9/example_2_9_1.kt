package com.example.coroutine_practice.ch_2.ch_2_9

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking {
   events()
       .onEach { event -> println("Event: $event") }
       .collect() // 스트림이 끝날 떼까지 기다린다.
    println("Done")
}

// addEventListener 대신 플로우의 onEach를 이용해 이벤트마다 onEach가 대응하게 할 수 있다. (이벤트를 대신 처리)
// 하지만 collect가 플로가 끝날 때 까지 기다리는 것이 문제이다.