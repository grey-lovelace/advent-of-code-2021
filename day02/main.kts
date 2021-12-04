check(150 == part1("sample.txt"))
println("Part1 = ${part1("input.txt")}")
check(900 == part2("sample.txt"))
println("Part2 = ${part2("input.txt")}")

fun part1(fileName: String) : Int {
    val input = java.io.File(fileName).readLines().map{ it.split(" ") }
    var (x,y) = listOf(0,0)
    input.forEach{ (command, amount) ->
        when (command) {
            "up" -> y -= amount.toInt()
            "down" -> y += amount.toInt()
            "forward" -> x += amount.toInt()
        }}
    return x * y
}

fun part2(fileName: String) : Int {
    val input = java.io.File(fileName).readLines().map{ it.split(" ") }
    var (x,y,aim) = listOf(0,0,0)
    input.forEach{ (command, amount) ->
        when (command) {
            "up" -> aim -= amount.toInt()
            "down" -> aim += amount.toInt()
            "forward" -> {
                x += amount.toInt()
                y += aim * amount.toInt()
            }}}
    return x * y
}