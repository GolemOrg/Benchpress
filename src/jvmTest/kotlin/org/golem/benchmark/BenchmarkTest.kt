package org.golem.benchmark

// TODO: Replace with better unit testing
fun main() {
    testSingleBenchmark()
    testTimedBenchmark()
    testOperationsBenchmark()
}

fun testSingleBenchmark() {
    val benchmark = Benchmark(
        name = "Single Benchmark",
        type = BenchmarkType.Single,
        targets = mutableListOf(
            BenchmarkTarget(
                name = "Target",
                callable = {
                    println("Hello World")
                }
            ),
        )
    )
    benchmark.start()
}

fun testTimedBenchmark() {
    val benchmark = Benchmark(
        name = "Timed Benchmark",
        type = BenchmarkType.Timed(1_000),
        targets = mutableListOf(
            BenchmarkTarget(
                name = "First Target",
                callable = {
                    // Perform intensive computation
                    for (i in 0..1_000_000) {
                        i * i
                    }
                }
            ),
            BenchmarkTarget(
                name = "Second Target",
                callable = {
                    // Perform easy computation
                    for (i in 0..1_000_000) {
                        i + i
                    }
                }
            )
        )
    )
    benchmark.start()
}

fun testOperationsBenchmark() {
    val benchmark = Benchmark(
        name = "Operations Benchmark",
        type = BenchmarkType.Operations(10_000),
        targets = mutableListOf(
            BenchmarkTarget(
                name = "First Target",
                callable = {
                    val list = mutableListOf<Int>()
                    for (i in 0..100_000) {
                        list.add(i)
                    }
                }
            ),
            BenchmarkTarget(
                name = "Second Target",
                callable = {
                    val list = mutableListOf<Int>()
                    for (i in 0..100_000) {
                        list.add(i)
                    }
                }
            )
        ),
    )
    benchmark.start()
}