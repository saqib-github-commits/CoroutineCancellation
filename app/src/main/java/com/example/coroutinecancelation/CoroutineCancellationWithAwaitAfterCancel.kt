package com.example.coroutinecancelation

import kotlinx.coroutines.*

fun coroutineCancellationWithAwaitAfterCancel() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    coroutineScope.launch {
        val job = async(Dispatchers.Default) {
            repeat(5) {
                println("Child Coroutine, counter: $it")
                delay(500L)
            }
            return@async 1
        }
        job.invokeOnCompletion {
            println("Parent Job completed")
        }
        delay(1000L)
        println("Canceling")
        job.cancel()
        println("Canceled")
        val result = job.await()
        println("Result :$result")
    }
}

