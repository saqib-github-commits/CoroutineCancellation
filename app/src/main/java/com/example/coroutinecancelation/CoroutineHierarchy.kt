package com.example.coroutinecancelation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun coroutineHierarchy () {
    val coroutineScope = CoroutineScope(Dispatchers.IO + Job())
    val parentJob = coroutineScope.launch {
        println("Parent Coroutine")
        val childJob1 = launch { println("Child Coroutine 1")
            launch {
                println("Further Child 1")
                println("Further Child 2")
            }
        }
        val childJob2 = launch { println("Child Coroutine 2") }
        val childJob3 = launch { println("Child Coroutine 3") }
    }
}