check(0 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(0 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input : String = file.readText()
    println(input)
    return 0
}

fun part2(file: java.io.File) : Int {
    val input : String = file.readText()
    println(input)
    return 0
}