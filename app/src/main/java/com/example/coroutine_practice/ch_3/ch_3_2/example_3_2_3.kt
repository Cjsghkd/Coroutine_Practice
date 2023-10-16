package com.example.coroutine_practice.ch_3.ch_3_2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.numbersFrom(start: Int) = produce {// SC (샌드 채널) + CC (코루틴 스코프)
    var x = start
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int): ReceiveChannel<Int> = produce {
    for (i in numbers) {
        if (i % prime != 0) { // 소수 필터
            send(i)
        }
    }
}

fun main() = runBlocking {
    var numbers = numbersFrom(2) // RC (리시브 채널)

    repeat(10) {
        val prime = numbers.receive() // 2를 가져온다. (numbers는 이제 3부터 시작한다)
        println(prime)
        numbers = filter(numbers, prime) // number: 3, 4, 5 ... | prime: 2 (루프를 돌때마다 numbers는 채널이 대체된다. (초기화)
    }
    println("완료")
    coroutineContext.cancelChildren()
}

// 실제로 이런식으로 파이프라인을 구현하지는 않고, 원한다면 디스패처를 이용해 CPU 자원을 효율적으로 이용하는 것이 가능하다.
// 위의 코드는 실용적인 코드라기보다는 이런식으로도 파이프라인을 짤 수 있다로 이해하기
