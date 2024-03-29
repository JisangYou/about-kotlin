package basic

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.Closeable

fun main() = runBlocking {
    val job = launch {
        SleepingBed().use {
            it.sleep(1000)
        }
    }
// try~finally 방식
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            println("main : I'm running finally!")
//        }
//    }

    delay(1300L)
    println("main : I'm tired of waiting!")
    job.cancelAndJoin()
    println("main : Now I can quit.")
}

class SleepingBed : Closeable {

    suspend fun sleep(times: Int) {
        repeat(times) {
            i -> println("I'm sleeping $i")
            delay(500L)
        }
    }

    override fun close() {
        println("main : I'm running close() in SleepingBed!")
    }

}
