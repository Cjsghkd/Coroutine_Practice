package com.example.coroutine_practice.ch_3.ch_3_3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

// 리턴값 리시브 채널
fun CoroutineScope.sayFast() = produce<String> {
    // 코루틴 스코프 + 샌드 채널
    while (true) {
        delay(100L)
        send("패스트")
    }
}

// 리턴값 리시브 채널
fun CoroutineScope.sayCampus() = produce<String> {
    // 코루틴 스코프 + 샌드 채널
    while (true) {
        delay(150L)
        send("캠퍼스")
    }
}

fun main() = runBlocking {
    val fasts = sayFast()
    val campuses = sayCampus()
    repeat(5) {
       select<Unit> { // 먼저 끝내는 애만 듣는다.
           fasts.onReceive {
               println("fast: $it")
           }
           campuses.onReceive {
               println("campus: $it")
           }
       }
    }
    delay(1000L)

}

// 먼저 끝나는 요청을 처리하는 것이 중요한 경우, select를 쓸 수 있다.
// 채널에 onReceive을 사용하는 것 이외에도 아래의 상황에서 사용 가능
// Job - onJoin
// Deferred - onAwait
// SendChannel - onSend
// ReceiveChannel - onReceive, onReceiveCatching
// delay - onTimeOut