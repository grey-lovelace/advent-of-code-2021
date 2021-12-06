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
    var tracker = (0..8).map{ i -> i to input.count{it==i}.toLong() }.toMap()
    (0 until days).forEach { _ ->
        var newDay = tracker.map{ (k,v) -> (k - 1) to v }.toMap().toMutableMap()
        newDay += mapOf(8 to newDay[-1]!!, 6 to (newDay[-1]!! + newDay[6]!!))
        newDay.remove(-1)
        tracker = newDay
    }
    return tracker.values.sum()
}