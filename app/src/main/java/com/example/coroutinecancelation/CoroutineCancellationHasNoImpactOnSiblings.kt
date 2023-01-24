package com.example.coroutinecancelation

import kotlinx.coroutines.*

fun coroutineCanceledHasNoImpactOnSiblings() {
    val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    coroutineScope.launch {

        val parentJob = launch {
            println("Parent Job")
            launch {
                delay(500L)
                println("Child Coroutine 1")
            }
            launch {
                delay(100L)
                throw CancellationException()
                println("Child Coroutine 2")
            }
            launch {
                delay(500L)
                println("Child Coroutine 3")
            }
        }

        parentJob.invokeOnCompletion {
            println("Parent Job completed")
        }
    }
}