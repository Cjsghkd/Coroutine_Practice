package com.example.coroutine_practice.ch_2.ch_2_2

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    (1..20).asFlow().filter {
        (it % 2) == 0 // filter의 조건을 술어(predicate) 라고도 부른다
    }.collect {
        println(it)
    }
}