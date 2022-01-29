package benchmark_kt

/**
 * The BenchmarkResult data class consists of:
 * - The targeted benchmark
 * - The time it took to run the benchmark
 * - The number of iterations ran during the benchmark
 */
data class BenchmarkResult(val target: BenchmarkTarget, val timeTaken: Long, val operations: Int) {

    /**
     * @return - The average operations processed per millisecond
     */
    fun getOperationsPerMs(): Long = operations / if (timeTaken == 0L) 1 else timeTaken

    override fun toString(): String = " - ${target.name}: $timeTaken ms, $operations operations, ${getOperationsPerMs()} operations/ms"
}