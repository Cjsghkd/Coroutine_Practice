package com.example.coroutine_practice.ch_1_6

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

suspend fun printRandom7() {
    delay(1000L)
    println(Random.nextInt(0, 500))
}

suspend fun printRandom8() {
    delay(500L)
    throw ArithmeticException()
}

val ceh3 = CoroutineExceptionHandler { _, exception ->
    println("Something happend: $exception")
}

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob() + ceh3)
    val job1 = scope.launch { printRandom7() }
    val job2 = scope.launch { printRandom8() }
    joinAll(job1, job2) // joinAll은 복수개의 Job에 대해 join를 수행하여 완전히 종료될 때까지 기다린다. (복수개를 한번에 join을 할 수 있다)
}

// SupervisorJob은 예외에 의한 취소를 아래쪽으로 내려가게 한다.
// 원래는 job2에서 취소가 난다면 부모 스코프에게 올라가서 다른 자식인 job1 스코프도 취소가 되지만,
// SupervisorJob을 사용했기 때문에 job2에서 난 에러는 job2의 자식에게만 영향이 간다.