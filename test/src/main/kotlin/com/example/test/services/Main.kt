package com.example.test.services

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main() {
    println("1")
    pool()
    println("2")
    thread {
        Thread.sleep(1000)
        println("3")
    }
    println("4")
    poolDaemon()
    poolExecutor()

    runBlocking() {
        launch {
            delay(1000)
            println("World")
        }
        println("Hello")
    }
}

fun pool() {
    thread(start = true) {
        println("running from thread(): ${Thread.currentThread()}")
    }
}

fun poolDaemon() {
    thread(isDaemon = true) {
        Thread.sleep(5000)
        println("running from thread(): ${Thread.currentThread()}")
    }
}

fun poolExecutor() {
    ThreadPoolExecutor(
        1,
        1,
        10,
        TimeUnit.SECONDS,
        LinkedBlockingQueue()
    )
}
