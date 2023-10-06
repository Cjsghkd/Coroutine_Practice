package com.example.coroutine_practice.ch_1.ch_1_7

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

suspend fun massiveRun1(action: suspend () -> Unit) {
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

@Volatile // 코틀린에서 어노테이션입니다.
var counter1 = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun1 {
            counter1++
        }
    }
    println("Counter = $counter1")
}

// Volatile를 적용했을 때 counter1++를 하면 보고 값을 증가시키는데 그 순간에 다른 스레드에서 또 증가가 되면 2가 증가되는게 아니라 1이 증가되는 경우도 있다.
// 이로인해 100000이 안될 수도 있다. (Volatile는 가시성 문제만 해결할 뿐 동시에 읽고 수정해서 생기는 문제를 해결하지 못한다)