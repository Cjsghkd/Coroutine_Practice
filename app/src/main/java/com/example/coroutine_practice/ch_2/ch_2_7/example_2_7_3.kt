package com.example.coroutine_practice.ch_2.ch_2_7

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple1()
        .catch { e -> emit("Caught $e") }
//        .catch { e -> throw  e }
        .collect { value -> println(value) }
}

// 빌더 코드 블록 내에서 예외를 처리하는 것은 예외 투명성을 어기는 것입니다.
// 플로우에서는 catch 연산자를 이용하는 것이 좋다.
// catch 블록에서 예외를 새로운 데이터로 만들어 emit 하거나, 다시 예외를 던지거나, 로그를 남길 수도 있다.