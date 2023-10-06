package com.example.coroutine_practice.ch_1.ch_1_1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {// 계층적, 구조적 (runBlocking은 그 속에 포함된 launch가 다 끝나기 전까지 종료되지 않는다)
        launch {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        }
        launch {
            println("launch2: ${Thread.currentThread().name}")
            println("1!")
        }
        println("runBlocking: ${Thread.currentThread().name}")
        delay(500L)
        println("2!")
    }
    print("4!")
}