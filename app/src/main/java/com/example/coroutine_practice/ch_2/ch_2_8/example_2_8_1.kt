package com.example.coroutine_practice.ch_2.ch_2_8

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun simple(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking {
    try {
        simple().collect { value -> println(value) }
    } finally {
        println("Done")
    }
}