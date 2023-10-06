package com.example.coroutine_practice.ch_1.ch_1_5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch {// runBlocking이 현재 메인 쓰레드에서 실행되기 때문에 메인세서 실행
        println("부모의 콘텍스트 / ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) {
        println("Default / ${Thread.currentThread().name}")
    }
    launch(Dispatchers.IO) {
        println("IO / ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        println("Unconfined / ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("Fast Campus")) {
        println("newSingleThreadContext / ${Thread.currentThread().name}")
    }
}

// Default는 코어 수에 비례하는 스레드 풀에서 수행합니다.
// IO는 코어 수 보다 훤씬 많은 스레드를 가지는 스레드 풀입니다. IO 작업은 CPU를 덜 소모하기 때문입니다.
// Unconfined는 어디에도 속하지 않습니다. 지금 시점에는 부모의 스레드에서 수행될 것 입니다. (앞으로 어디 스레드에서 실행될지 모른다. 이로 인해 잘 쓰이지 않는다)
// newSingleThreadContext는 항상 새로운 스레드를 만듭니다.