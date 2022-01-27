# benchmark-kt
A small benchmarking library built in Kotlin


## Benchmark Types
 - `BenchmarkType.Single` - This runs each of the benchmark target once (This should mainly be used for operations like I/O, REST, etc.)
 - `BenchmarkType.Timed(milliseconds: Long) - This runs each target for `milliseconds` milliseconds.
 - `BenchmarkType.Iterations(iterations: Int)` - This runs each target for `iterations` iterations

## Benchmark Targets

At its core, the benchmark is made up of targets. These targets are all of the `BenchmarkTarget` type.
Creating a simple target looks like this:
```kt
val target = BenchmarkTarget(
    // The name of the target (Used in logging)
    name = "Test Target"
    // The actual block to execute when running
    callable = {
        for (i in 0..1_000) {
            val squared = i * i
        }
    }
)
```

## Creating a benchmark

Creating a benchmark is as easy as creating a `Benchmark` object.
A simple benchmark creation looks like this:
```kt
val benchmark = Benchmark(
    // The name used for logging & other tracking
    name = "Single Benchmark",
    // The type of benchmark to run (Single, Timed(milliseconds: Long), Iterations(iterations: Int)
    type = BenchmarkType.Single,
    // The list of targets to benchmark
    targets = mutableListOf(
        BenchmarkTarget(
            name = "Target",
            callable = {
                println("Hello World")
            }
        ),
    ),
    // The amount of milliseconds to run the benchmarks (as a way to warm up the JVM)
    warmupMilliseconds: Long = 10_000
)
```
After creating the benchmark object, the benchmark can be ran using `benchmark.start()`.
This will automatically log the information into the console.

## Roadmap
As of now, there are still many things that can be improved upon for this library. This will help keep track of what is implemented.

- [ ] An option to output the results into log files
- [ ] A list of results that can be read after the benchmark is complete
- [ ] Configuration files to remove a lot of boilerplate
- [ ] An option to prevent direct access to the console (i.e., preventing debug logs printing to the screen during benchmarking)
