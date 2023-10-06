package com.example.coroutine_practice.ch_1.ch_1_7

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

suspend fun massiveRun2(action: suspend () -> Unit) {
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

var counter2 = AtomicInteger()

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun2 {
            counter2.incrementAndGet()
            // incrementAndGet을 통해 값을 증가하고 가져오면서 그 사이에 다른 스레드가 값을 바뀌는걸 막는다
            // 이 때문에 AtomicInteger를 스레드 안전한 자료구조이다.
        }
    }
    println("Counter = $counter2")
}

// AtomicInteger와 같은 스레드 안전한 자료구조를 사용하는 방법이 있다.
// 이 문제에서는 맞았지만 AtomicInteger가 항상 정답은 아니다.