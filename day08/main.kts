check(26 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(61229 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    return file.readLines().flatMap{ it.split("|")[1].trim().split(" ") }
        .count{ listOf(2,3,4,7).contains(it.length) }
}

fun part2(file: java.io.File) : Int {
    fun String.abc() = String(toCharArray().apply{ sort() })
    fun matchByLettersInCommon( searchList: List<String>?, matchString: String?, lettersToMatchOn: Int ) =
        searchList!!.first{ it.count{ letter -> matchString!!.contains(letter) } == lettersToMatchOn }
    return file.readLines().map{ line ->
        val (otherData, output) = line.split("|").map{ it.trim().split(" ") }
        val lengthMap : Map<Int,List<String>> = (otherData + output).distinct().groupBy{it.length}
        val decodeMap = mutableMapOf<Int,String>()
        decodeMap[1] = lengthMap[2]!![0]
        decodeMap[4] = lengthMap[4]!![0]
        decodeMap[7] = lengthMap[3]!![0]
        decodeMap[8] = lengthMap[7]!![0]
        decodeMap[2] = matchByLettersInCommon(lengthMap[5], decodeMap[4], 2)
        decodeMap[3] = matchByLettersInCommon(lengthMap[5], decodeMap[1], 2)
        decodeMap[5] = matchByLettersInCommon(lengthMap[5], decodeMap[2], 3)
        decodeMap[0] = matchByLettersInCommon(lengthMap[6], decodeMap[5], 4)
        decodeMap[6] = matchByLettersInCommon(lengthMap[6], decodeMap[1], 1)
        decodeMap[9] = matchByLettersInCommon(lengthMap[6], decodeMap[4], 4)
        var switchedDecoder = decodeMap.map{ it.value.abc() to it.key }.toMap()
        output.map{ switchedDecoder[it.abc()].toString() }.joinToString("").toInt()
    }.sum()
}