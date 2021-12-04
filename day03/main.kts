check(198 == part1("sample.txt"))
println("Part1 = ${part1("input.txt")}")
check(230 == part2("sample.txt"))
println("Part2 = ${part2("input.txt")}")

fun part1(fileName: String) : Int {
    val input = java.io.File(fileName).readLines()
    var gamma = input[0].indices.map{ input.mostCommonByIndex(it) }.joinToString("")
    var epsilon = gamma.map{ it.opp() }.joinToString("")
    return gamma.toInt(2) * epsilon.toInt(2)
}

fun part2(fileName: String) : Int {
    val input = java.io.File(fileName).readLines()
    var (oxy, co2) = listOf(input.toList(),input.toList())
    input[0].indices.forEach{ i ->
        if(oxy.size > 1) oxy = oxy.filter{ it.get(i) == oxy.mostCommonByIndex(i) }
        if(co2.size > 1) co2 = co2.filter{ it.get(i) == co2.mostCommonByIndex(i).opp() }
    }
    return oxy[0].toInt(2) * co2[0].toInt(2)
}

fun Char.opp() : Char { return if (this=='0') '1' else '0' }

fun List<String>.mostCommonByIndex(i : Int) : Char {
    return if (.5 <= (this.map { it[i] }.count { it=='1' }) / this.size.toDouble()) '1' else '0'
}