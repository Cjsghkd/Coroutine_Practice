package com.example.coroutine_practice.ch_1_7

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

suspend fun massiveRun4(action: suspend () -> Unit) {
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

val mutex = Mutex()
var counter4 = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun4 {
            mutex.withLock {
                counter4++
            }
        }
    }
    println("Counter = $counter4")
}

// 뮤텍스는 상호배제(Mutual exclusion)의 줄임말입니다.
// 공유 상태를 수정할 때 임계 영역(critical section)을 이용하게 하며, 임계 영역을 동시에 접근하는 것을 허용하지 않습니다.
// 임계 영역에는 한 번에 한 사람(스레드)만 들어갈 수 있는 느낌이다.

