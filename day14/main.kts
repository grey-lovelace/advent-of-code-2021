check(1588L == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(2188189693529 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Long {
    return calculate(file, 10)
}

fun part2(file: java.io.File) : Long {
    return calculate(file, 40)
}

fun calculate(file: java.io.File, steps: Int): Long{
    val input = file.readLines()
    var template = input[0]
    var templateMap = (1..template.lastIndex)
        .map{ "${template[it-1]}${template[it]}" }
        .groupingBy{it}.eachCount().map{ it.key to it.value.toLong() }.toMap()
    val rules = input.takeLast(input.size-2).map{ it.substring(0..1) to it.last()}.toMap()
    var letterCount = rules.values.distinct().map{ it to 0L }.toMap().toMutableMap()
    template.forEach{ letterCount[it] = letterCount[it]!! + 1L }
    (0 until steps).forEach{ _ ->
        var newMap = rules.keys.map{ it to 0L }.toMap().toMutableMap()
        templateMap.forEach{
            newMap["${it.key[0]}${rules[it.key]}"] = newMap["${it.key[0]}${rules[it.key]}"]!! + it.value
            newMap["${rules[it.key]}${it.key[1]}"] = newMap["${rules[it.key]}${it.key[1]}"]!! + it.value
            letterCount[rules[it.key]!!] = letterCount[rules[it.key]!!]!! + it.value
        }
        templateMap = newMap
    }
    val sorted = letterCount.values.sorted()
    return sorted.last() - sorted[0]
}