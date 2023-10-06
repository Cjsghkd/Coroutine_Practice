package com.example.coroutine_practice.ch_1_5

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalStdlibApi::class)
fun main() = runBlocking<Unit> {
    launch {// A (부모 컨텐스트)
        launch(Dispatchers.IO + CoroutineName("launch1")) { // A + CoroutineContext(Dispatchers.IO, CoroutineName("launch1"))
            println("launch1: ${Thread.currentThread().name}")
            println(coroutineContext[CoroutineDispatcher])
            println(coroutineContext[CoroutineName])
            delay(5000L)
        }

        launch(Dispatchers.Default + CoroutineName("launch2")) {
            println("launch2: ${Thread.currentThread().name}")
            println(coroutineContext[CoroutineDispatcher])
            println(coroutineContext[CoroutineName])
            delay(10L)
        }
    }
}

// 여러 코루틴 엘리먼트를 한번에 사용할 수 있다.
// + 연산으로 엘리먼트를 합치면 된다.
// 합쳐진 엘리먼트는 coroutineContext[XXX]로 조회할 수 있다.

