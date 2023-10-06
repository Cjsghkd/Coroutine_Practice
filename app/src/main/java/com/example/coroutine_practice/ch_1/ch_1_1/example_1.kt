package com.example.coroutine_practice.ch_1.ch_1_1

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println(Thread.currentThread().name)
    println("Hello")
}