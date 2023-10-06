package com.example.coroutine_practice.ch_1.ch_1_7

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

suspend fun massiveRun3(action: suspend () -> Unit) {
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

var counter3 = 0
val counterContext = newSingleThreadContext("CounterContext")

fun main() = runBlocking {
    withContext(counterContext) { // 전체 코드를 하나의 스레드에서
        massiveRun3 {
            counter3++
        }
    }
    println("Counter = $counter3")
}

//fun main() = runBlocking { 이런식으로 작업을 다 감싸는게 아닌 특정 동작때만 감싸서 해도 된다. (스레드 한정을 다르게 해도 된다)
//    withContext(Dispatchers.Default) {
//        massiveRun3 {
//            withContext(counterContext) { 더하는 코드를 하나의 스레드에서
//                counter3++
//            }
//        }
//    }
//    println("Counter = $counter3")
//}

// newSingleThreadContext를 이용해서 특정 스레드를 만들어 그 스레드에서만 작업이 일어나게 한다.
// 스레드 한정도 정답은 없다. 상황마다 다르다
