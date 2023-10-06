package com.example.coroutine_practice.ch_1_7

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

// 액터는 액터가 독점적으로 자료를 가지며 그 자료를 다른 코루틴과 공유하지 않고 액터를 통해서만 접근하게 만든다.

sealed class CounterMsg
object IncCounter: CounterMsg()
class GetCounter(val response: CompletableDeferred<Int>): CounterMsg()

// 실드(sealed) 클래스는 외부에서 확장이 불가능한 클래스이다. CounterMsg는 IncCounter와 GetConter 두 종류로 한정됩니다.
// IncCounter는 싱글톤(object)으로 인스턴스를 만들 수 없습니다. 액터에게 값을 증가시키기 위한 신호로 쓰입니다.
// GetCounter는 값을 가져올 때 쓰며 CompletableDeferred<Int>를 이용해 값을 받아옵니다.
// CompletableDeferred는 나중에 response를 이용해 값을 받는 타입이라고 생각하면 된다.

fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter5 = 0 // 액터 안에 상태를 캡슐화 해두고 다른 코루틴이 접근 하지 못하게 합니다.

    for (msg in channel) { // suspension point
        // 외부에서 보내는 것은 채널을 통해서만 받을 수 있습니다. (receive)
        when (msg) {
            is IncCounter -> counter5++ // 증가시키는 신호
            is GetCounter -> msg.response.complete(counter5) // 현재 상태를 반환합니다. (counter 값을 알게 된다)
        }
    }
}

// 채널은 송신 측에서 값을 send 할 수 있고 수신 측에서 receive를 할 수 있는 도구이다.

suspend fun massiveRun5(action: suspend () -> Unit) {
    val n = 100 // 시작할 코루틴의 갯수
    val k = 1000 // 코루틴 내에서 반복할 횟수
    val elapsed = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("$elapsed ms 동안 ${n * k}개의 액션을 수행했습니다.")
}

fun main() = runBlocking<Unit> {
    val counter5 = counterActor()
    withContext(Dispatchers.Default) {
        massiveRun5 {
            counter5.send(IncCounter) // suspension point
        }
    }

    val response = CompletableDeferred<Int>()
    counter5.send(GetCounter(response))
    println("Counter = ${response.await()}") // suspension point가 되었다가 액터가 값을 반환해주면 수행된다.
    counter5.close()
}

