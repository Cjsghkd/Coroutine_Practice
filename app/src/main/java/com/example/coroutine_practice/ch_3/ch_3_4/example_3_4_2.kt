package com.example.coroutine_practice.ch_3.ch_3_4

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<Int>(Channel.RENDEZVOUS) // 채널 버퍼 사이즈 0
    launch {
        for (x in 1..20) {
            println("$x 전송중")
            channel.send(x) // 받든 안받든 채널로 계속 보낸다.
        }
        channel.close()
    }

    for (x in channel) {
        println("$x 수신")
        delay(100L)
    }
    println("완료")
}

// 랑데뷰는 버퍼 사이즈를 0으로 지정하는 것이다.
// 생성사에 사이즈를 전달하지 않으면 랑데뷰가 디폴트이다.
// 랑데뷰 외에도 사이즈 대신 사용할 수 있는 다른 설정 값이 있다.
// UNLIMITED - 무제한으로 설정 (메모리가 부족해지면 RunTime 에러가 발생한다)
// CONFLATED - 오래된 값이 지워짐
// BUFFERED - 64개의 버퍼, 오버플로우엔 suspend

