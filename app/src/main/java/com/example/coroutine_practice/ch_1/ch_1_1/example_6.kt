package com.example.coroutine_practice.ch_1.ch_1_1

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        println("launch: ${Thread.currentThread().name}")
        println("World!")
    }
    println("runBlocking: ${Thread.currentThread().name}")
    Thread.sleep(500)
    println("Hello")
}