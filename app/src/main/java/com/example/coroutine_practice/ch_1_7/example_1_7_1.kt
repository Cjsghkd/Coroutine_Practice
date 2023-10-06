package com.example.coroutine_practice.ch_1_7

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

suspend fun massiveRun(action: suspend () -> Unit) {
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

var counter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            counter++
        }
    }
    println("Counter = $counter")
}

// withContext는 수행이 완료될 때까지 기다리는 코루틴 빌더이다.
// 뒤에 println("Counter = $counter")은 withContext 블록의 코드가 모두 수행되면 호출됩니다.
// runBlocking은 스레드를 잡고 있는 느낌이라면 withContext는 suspend 시키는 느낌입니다. (재운다?)
// counter가 매번 100000이 되지 않는다. 왜냐면 여러 스레드간 다 다른 정보를 가지고 있기 때문이다.