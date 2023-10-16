package com.example.coroutine_practice.ch_2.ch_2_9

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun log(msg: String) = println("${Thread.currentThread().name}: $msg")

fun main() = runBlocking {// this: 코루틴 스코프, 코루틴
   events()
       .onEach { event -> log("Event: $event") }
       .launchIn(this) // 코루틴 스코프 // 새로운 코루틴을 만들어내서 거기서 onEach를 실행시킨다
    log("Done")
}

// launchIn을 이용하면 별도의 코루틴에서 플로우를 런칭할 수 있다.