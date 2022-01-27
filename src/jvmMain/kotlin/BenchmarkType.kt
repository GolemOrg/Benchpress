

sealed class BenchmarkType{
    /**
     * `BenchmarkType.Single` will run the benchmark once and return the result.
     */
    object Single : BenchmarkType()

    /**
     * `BenchmarkType.Timed(time)` will run the benchmark for `time` milliseconds.
     */
    data class Timed(val time: Long) : BenchmarkType()
    /**
     * `BenchmarkType.Iterations(iterations)` will run the benchmark for `iterations` times.
     */
    data class Operations(val iterations: Int): BenchmarkType()
}