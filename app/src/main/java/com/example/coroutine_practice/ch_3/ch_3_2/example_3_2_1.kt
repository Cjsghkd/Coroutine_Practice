package com.example.coroutine_practice.ch_3.ch_3_2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++) // 1, 2, 3, 4, 5, 6 ...
    }
}

fun CoroutineScope.produceStringNumbers(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    for (i in numbers) {
        send("${i}!") // "1!", "2!", "3!" ...
    }
}

fun main() = runBlocking {
    val numbers = produceNumbers() // numbers: 리시브 채널 (receive 메서드만 사용 가능하고 send 메서드는 사용 불가능) [일반 채널은 receive, send 메서드 둘 다 사용 가능]
    val stringNumbers = produceStringNumbers(numbers)

    repeat(5) {// 위에 정의한 함수에 close가 없다 (for (x in stringNumbers) 를 쓸 수 없다) [무한 로딩]
        println(stringNumbers.receive()) // 위의 이유 때문에 명시적으로 receive를 호출한다. (횟수를 정해놓고 호출)
    }
    println("완료")
    coroutineContext.cancelChildren() // produceNumbers, produceStringNumbers 모두 취소
}

// 파이프라인은 하나의 스트림을 프로듀서가 만들고, 다른 코루틴에서 그 스트림을 읽어 새로운 스트림을 만드는 패턴이다.
// 여러가지 채널을 통해서 데이터를 가공할 수 있다.
// 위 코드와 같이 여러개의 채널이 순차적으로 실행되는 것을 파이프라인이라고 한다.