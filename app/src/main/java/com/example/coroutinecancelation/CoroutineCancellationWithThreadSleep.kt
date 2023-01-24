package com.example.coroutinecancelation

import kotlinx.coroutines.*

private fun coroutineCancellationWithThreadSleep() {
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    coroutineScope.launch {
        val job = launch {
            println("Parent Coroutine")
            withContext(Dispatchers.IO) {
                var counter = 1
                while (counter <= 5) {
                    println("Child Coroutine, counter: $counter")
                    Thread.sleep(500)
                    counter += 1
                }
            }
        }
        delay(1000L)
        job.invokeOnCompletion {
            println("Parent Job completed")
        }
        println("Canceling")
        job.cancel()
        println("Canceled")
    }
}