check(7 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(5 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    val input = file.readLines().map{ it.toInt() }
    return (1..input.lastIndex).count{ input[it] > input[it-1] }
}

fun part2(file: java.io.File) : Int {
    val input = file.readLines().map{ it.toInt() }
    val slides = input.windowed(3,1).map{ it.sum() }
    return (1..slides.lastIndex).count{slides[it] > slides[it-1] }
}