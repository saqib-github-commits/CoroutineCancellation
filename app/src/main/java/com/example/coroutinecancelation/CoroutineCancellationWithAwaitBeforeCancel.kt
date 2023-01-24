package com.example.coroutinecancelation

import kotlinx.coroutines.*

fun coroutineCancellationWithAwaitBeforeCancel() {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    coroutineScope.launch {
        val job = async {
            repeat(5) {
                println("Child Coroutine, counter: $it")
                delay(500L)
            }
            return@async 1
        }
        job.invokeOnCompletion {
            println("Parent Job completed")
        }
        val result = job.await()
        println("Result :$result")
        println("Canceling")
        job.cancel()
        println("Canceled")
    }
}