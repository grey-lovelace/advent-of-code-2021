assert(0 == part1("sample.txt"))
println("Part1 = ${part1("input.txt")}")
assert(0 == part2("sample.txt"))
println("Part2 = ${part2("input.txt")}")

fun part1(fileName: String) : Int {
    val input : String = java.io.File(fileName).readText()
    println(input)
    return 0
}

fun part2(fileName: String) : Int {
    val input : String = java.io.File(fileName).readText()
    println(input)
    return 0
}