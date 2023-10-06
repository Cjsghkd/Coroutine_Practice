package com.example.coroutine_practice.ch_1.ch_1_4

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

suspend fun getRandom3(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

suspend fun getRandom4(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val value1 = async { // this: 코루틴
            com.example.coroutine_practice.ch_1.ch_1_4.getRandom3()
        }
        val value2 = async { // this: 코루틴
            com.example.coroutine_practice.ch_1.ch_1_4.getRandom4()
        }
        // job.join() + 결과도 가져온다.
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}") // suspension
    }
    println(elapsedTime)
}

// async 키워드를 이용하면 동시에 다른 블록을 수행할 수 있다.
// launch와 비슷해 보이지만 await 키워드를 통해 수형결과를 받아올 수 있다는 차이가 있다.
// 결과를 받아야 한다면 async, 받지 않아도 된다면 launch를 선택할 수 있다.
// await 키워드를 만나면 async 블록이 수행이 끝났는지 확인하고 아직 끝나지 않았다면 suspend(일시중지) 되었다 나중에 다시 깨어나고 반환값을 받아온다.
// async와 await은 일반적으로 같이 쓰인다. 왜냐면 값을 가져올 필요가 없다면 launch를 쓰면 되고 async를 썼다는 거는 값을 가져와야 한다는 거기 때문이다,