package com.example.coroutine_practice.ch_1.ch_1_1

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        println("launch: ${Thread.currentThread().name}")
        println("World!")
    }
    println("runBlocking: ${Thread.currentThread().name}")
    println("Hello")
}

// launch는 할 수 있다면 다른 코루틴 코드를 같이 수행시키는 코루틴 빌더입니다.
// launch 코루틴 빌더에 있는 내용이 runBlocking이 있는 메인 흐름보다 늦게 수행된 것을 볼 수 있습니다.
// 둘 다 메인 스레드를 사용하기 때문에 runBlocking의 코드들이 메인 스레드를 다 사용할 때 까지 launch의 코드 블록이 기다린다.
// runBlocking은 Hello를 출력하고 나서 종료하지는 않고 launch 코드블록의 내용이 다 끝날 때까지 기다린다.