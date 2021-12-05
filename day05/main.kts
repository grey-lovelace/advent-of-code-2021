check(5 == part1("sample.txt"))
println("Part1 = ${part1("input.txt")}")
check(12 == part2("sample.txt"))
println("Part2 = ${part2("input.txt")}")

fun part1(fileName: String) : Int {
    return countPoints(fileName, false)
}

fun part2(fileName: String) : Int {
    return countPoints(fileName, true)
}

fun countPoints(fileName: String, includeDiagonals: Boolean): Int {
    return java.io.File(fileName).readLines().map{
        Line(Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)").find(it)!!.destructured.toList())
    }.filter{ includeDiagonals || !it.isDiagonal }
    .flatMap{ it.allPoints() }
    .groupingBy{ it }.eachCount()
    .count{ (point,timesOccurred) -> timesOccurred  > 1 }
}

class Line(pointList: List<String>){
    val x1 = pointList[0].toInt(); val y1 = pointList[1].toInt(); val x2 = pointList[2].toInt(); val y2 = pointList[3].toInt()
    val isDiagonal = x1 != x2 && y1 != y2
    fun allPoints(): List<Pair<Int, Int>> {
        var xs = (minOf(x1,x2)..maxOf(x1,x2)).toList()
        if(x2<x1) xs = xs.reversed()
        var ys = (minOf(y1,y2)..maxOf(y1,y2)).toList()
        if(y2<y1) ys = ys.reversed()
        return (0..maxOf(xs.lastIndex, ys.lastIndex)).map{ i -> Pair(xs[minOf(i,xs.lastIndex)],ys[minOf(i,ys.lastIndex)]) }
    }
}