package com.example.coroutine_practice.ch_2.ch_2_3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple(): Flow<Int> = flow {
    log("flow를 시작합니다.")
    for (i in 1..10) {
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO) {
        simple().collect { value ->
            log("${value}를 받음")
        }
    }
}

// 플로우는 현재 코루틴 컨텍스트에서 호출 됩니다. (지금은 launch(Dispatchers.IO)에서 호출 됩니다)