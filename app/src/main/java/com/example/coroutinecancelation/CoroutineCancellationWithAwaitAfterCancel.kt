package com.example.coroutinecancelation

import kotlinx.coroutines.*

fun coroutineCancellationWithAwaitAfterCancel() {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    coroutineScope.launch {
        val job = async {
            repeat(5) {
                println("Child Coroutine, counter: $it")
                delay(500L)
            }
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

