package com.example.coroutine_practice.ch_2.ch_2_8

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple()
        .map {
            if (it > 2) {
                throw IllegalStateException()
            }
            it + 1
        }
        .onCompletion { println("Done") } // finally과 똑같은 역할 가능
        .collect { value -> println(value) }
}