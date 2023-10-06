package com.example.coroutine_practice.ch_1_5

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val elapsed = measureTimeMillis {
        val job = launch {// 부모 (자식이 끝날 때까지 기다린다)
            launch {// 자식 1
                println("launch1: ${Thread.currentThread().name}")
                delay(5000L)
            }

            launch {// 자식 2
                println("launch2: ${Thread.currentThread().name}")
                delay(10L)
            }
        }
        job.join()
    }
    println(elapsed)
}

