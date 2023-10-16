package com.example.coroutine_practice.ch_3.ch_3_3

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun someone(channel: Channel<String>, name: String) {
    for (comment in channel) {
        println("${name}: $comment")
        channel.send(comment.drop(1) + comment.first()) // "스트 캠퍼스" + "패" => "스트 캠퍼스패
        delay(100L)
    }
}

fun main() = runBlocking<Unit> {
    val channel = Channel<String>() // Channel = Receive Channel + Send Channel
    launch {
        someone(channel, "민준")
    }
    launch {
        someone(channel, "서연")
    }
    channel.send("패스트 캠퍼스")
    delay(1000L)
    coroutineContext.cancelChildren()
}

// 두 개의 코루틴에서 채널을 서로 사용할 때 공정하게 기회를 준다.