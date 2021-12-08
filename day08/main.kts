check(26 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(61229 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    return file.readLines().flatMap{ it.split("|")[1].trim().split(" ") }
        .count{ listOf(2,3,4,7).contains(it.length) }
}

fun part2(file: java.io.File) : Int {
    return file.readLines().map{ line ->
        val (otherData, output) = line.split("|").map{ it.trim().split(" ") }
        val allValsMap = (otherData + output).distinct().groupBy{it.length}
        val finalMap = mutableMapOf(
            1 to allValsMap[2]!![0],
            7 to allValsMap[3]!![0],
            4 to allValsMap[4]!![0],
            8 to allValsMap[7]!![0]
        )
        finalMap += 2 to allValsMap[5]!!.first{ it.count{ letter -> finalMap[4]!!.contains(letter) } == 2 }
        finalMap += 3 to allValsMap[5]!!.first{ it.count{ letter -> finalMap[1]!!.contains(letter) } == 2 }
        finalMap += 5 to allValsMap[5]!!.first{ it.count{ letter -> finalMap[2]!!.contains(letter) } == 3 }
        finalMap += 0 to allValsMap[6]!!.first{ it.count{ letter -> finalMap[5]!!.contains(letter) } == 4 }
        finalMap += 9 to allValsMap[6]!!.first{ it.count{ letter -> finalMap[4]!!.contains(letter) } == 4 }
        finalMap += 6 to allValsMap[6]!!.first{
            it.count{ letter -> finalMap[0]!!.contains(letter) } == 5 &&
            it.count{ letter -> finalMap[9]!!.contains(letter) } == 5
        }
        val finalMapSwitched = finalMap.map{ it.value.sort() to it.key }.toMap()
        output.map{ finalMapSwitched[it.sort()].toString() }.joinToString("").toInt()
    }.sum()
}

fun String.sort() = String(toCharArray().apply { sort() })

