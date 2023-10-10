package com.example.coroutine_practice.ch_2.ch_2_7

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple1()
        .catch { e -> emit("Caught $e") }
        .collect { value -> println(value) }
}

// catch 연산자는 업스트림(catch 연산자를 쓰기 전의 코드)에만 영향을 미치고 다운스트림에는 영향을 미치지 않ㄴ느다.
// 이를 catch 투명성이라고 한다.