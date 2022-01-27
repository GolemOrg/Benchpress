import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.measureTimeMillis

data class BenchmarkTarget(val name: String, val callable: () -> Unit)

/**
 * The Benchmark class is used to run benchmarks.
 * The class consists of the following:
 * - The name (to be used for logging and tracking)
 * - The type (@see BenchmarkType)
 * - A list of BenchmarkTargets
 * - (Optionally) How long to run warmups for
 */
class Benchmark(
    private val name: String,
    private val type: BenchmarkType,
    private val targets: List<BenchmarkTarget>,
    private val warmupMilliseconds: Int = 10_000,
) {

    private fun benchmark(target: BenchmarkTarget): BenchmarkResult {
        log("Beginning benchmark for target '${target.name}'...")
        var timeTaken = 0L
        var operations = 0
        when(type) {
            is BenchmarkType.Single -> {
                timeTaken = measureTimeMillis {
                    target.callable()
                }
                operations = 1
            }
            is BenchmarkType.Timed -> {
                val start = System.currentTimeMillis()
                while(System.currentTimeMillis() - start < type.time) {
                    target.callable()
                    operations++
                }
                timeTaken = System.currentTimeMillis() - start
            }
            is BenchmarkType.Operations -> {
                timeTaken = measureTimeMillis {
                    for (i in 0 until type.iterations) {
                        target.callable()
                    }
                }
                operations = type.iterations
            }
        }
        log("Benchmarking for target '${target.name}' complete.")
        return BenchmarkResult(target, timeTaken, operations)
    }

    private fun warmup() {
        val start = System.currentTimeMillis()
        while(System.currentTimeMillis() - start < warmupMilliseconds) {
            for (target in targets) {
                target.callable()
            }
        }
    }

    private fun test(): List<BenchmarkResult> {
        val results = mutableListOf<BenchmarkResult>()
        for (target in targets) {
            results.add(benchmark(target))
        }
        return results
    }

    private fun log(message: String, type: String = "INFO") {
        println("[%s] [Benchmark: %s] [%s] %s".format(
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Date()),
            name,
            type,
            message
        ))
    }

    fun start() {
        log("Beginning benchmark...")
        log("Warming JVM up for $warmupMilliseconds milliseconds...")
        warmup()
        log("Warmup complete.")
        log("Moving onto main benchmark...")
        val results = test()
        log("Benchmark complete.")
        printResults(results)
    }

    private fun printResults(results: List<BenchmarkResult>) {
        val header = "------------ Results for '$name' ------------"
        println(header)
        results.forEach { println(it) }
        println("-".repeat(header.length))
    }

}