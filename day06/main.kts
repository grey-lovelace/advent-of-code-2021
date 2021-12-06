check(5934.toLong() == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(26984457539.toLong() == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Long {
    return calcFish(file, 80)
}

fun part2(file: java.io.File) : Long {
    return calcFish(file, 256)
}

fun calcFish(file: java.io.File, days: Int): Long{
    var input = file.readText().split(",").map{ it.toInt() }
    var tracker = (0..8).map{ i -> i to input.count{it==i}.toLong() }
        .toMap().toMutableMap()
    (0 until days).forEach { _ ->
        tracker = tracker.map{ (k,v) -> (k - 1) to v }.toMap().toMutableMap()
        tracker += mapOf(8 to tracker[-1]!!, 6 to (tracker[-1]!! + tracker[6]!!))
        tracker.remove(-1)
    }
    return tracker.values.sum()
}