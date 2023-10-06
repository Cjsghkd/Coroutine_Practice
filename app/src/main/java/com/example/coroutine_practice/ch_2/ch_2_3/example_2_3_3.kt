package com.example.coroutine_practice.ch_2.ch_2_3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun log2(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple2(): Flow<Int> = flow {
    for (i in 1..10) {
        delay(100L)
        log2("값 ${i}를 emit 합니다.")
        emit(i)
    } // 업스트림 (Dispatchers.Default)
}.flowOn(Dispatchers.Default) // 위치에 따라 업스트림, 다운스트립이 구분된다.

//fun simple2(): Flow<Int> = flow {
//    for (i in 1..10) {
//        delay(100L)
//        log2("값 ${i}를 emit 합니다.")
//        emit(i)
//    } // 업스트림 (Dispatchers.IO)
//}.flowOn(Dispatchers.IO).map {
//    it * 2
//} // 업스트림 (Dispatchers.Default)
//    .flowOn(Dispatchers.Default).flowOn(Dispatchers.IO) // 앞에 것으로 작동

fun main() = runBlocking<Unit> {
    simple2().collect { value -> // 다운스트림
        log2("${value}를 받음")
    }
}

// flowOn 연산자를 통해 컨텍스트를 올바르게 바꿀 수 있다.
// 업스트림만 flowOn에서 정한 컨텍스트로 실행된다.