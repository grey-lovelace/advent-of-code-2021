check(37 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(168 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input = file.readText().split(',').map{ it.toInt() }
    return (input.minOrNull()!!..input.maxOrNull()!!).map{ pos ->
            input.map{ kotlin.math.abs(it-pos) }.sum()
        }.minOrNull()!!
}

fun part2(file: java.io.File) : Int {
    val input = file.readText().split(',').map{ it.toInt() }
    return (input.minOrNull()!!..input.maxOrNull()!!).map{ pos ->
            input.map{ kotlin.math.abs(it-pos ) }.map{ it*(it+1)/2 }.sum()
        }.minOrNull()!!
}