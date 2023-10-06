package com.example.coroutine_practice.ch_2.ch_2_3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun log1(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple1(): Flow<Int> = flow { // 플로우 내에서는 컨텍스트를 바꿀 수 없다.
    withContext(Dispatchers.Default) {// 에러 발생
        for (i in 1..10) {
            delay(100L)
            emit(i)
        }
    }
}

fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO) {
        simple1().collect { value ->
            log1("${value}를 받음")
        }
    }
}