package com.example.coroutine_practice.ch_1_1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun doThree() {
    println("launch1: ${Thread.currentThread().name}")
    delay(1000L)
    println("3!")
}

fun doOne() { // delay와 같은 코루틴 내에서만 작동하는 함수를 쓰지 않았으니 suspend를 굳이 안 붙여줘도 된다.
    println("launch2: ${Thread.currentThread().name}")
    println("1!")
}

suspend fun doTwo() {
    println("runBlocking: ${Thread.currentThread().name}")
    delay(500L)
    println("2!")
}

fun main() = runBlocking {
    launch {
        doThree()
    }
    launch {
        doOne()
    }
    doTwo()
}

// delay, launch 등의 함수들은 코루틴 내에서만 호출 할 수 있습니다. 함수로 분리해서 쓰고 싶을 때는 함수 앞에 suspend 붙이기