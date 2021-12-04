check(7 == part1("sample.txt"))
println("Part1 = ${part1("input.txt")}")
check(5 == part2("sample.txt"))
println("Part2 = ${part2("input.txt")}")

fun part1(fileName: String) : Int {
    val input = java.io.File(fileName).readLines().map{ it.toInt() }
    return (1..input.lastIndex).count{ input[it] > input[it-1] }
}

fun part2(fileName: String) : Int {
    val input = java.io.File(fileName).readLines().map{ it.toInt() }
    val slides = input.windowed(3,1).map{ it.sum() }
    return (1..slides.lastIndex).count{slides[it] > slides[it-1] }
}