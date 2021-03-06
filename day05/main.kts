check(5 == part1(java.io.File("sample.txt")))
println("Part1 = ${part1(java.io.File("input.txt"))}")
check(12 == part2(java.io.File("sample.txt")))
println("Part2 = ${part2(java.io.File("input.txt"))}")

fun part1(file: java.io.File) : Int {
    return countPoints(file, false)
}

fun part2(file: java.io.File) : Int {
    return countPoints(file, true)
}

fun countPoints(file: java.io.File, includeDiagonals: Boolean): Int {
    return file.readLines().map{
        Line(Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)").find(it)!!.destructured.toList())
    }.filter{ includeDiagonals || !it.isDiagonal }
    .flatMap{ it.allPoints() }.groupingBy{ it }.eachCount()
    .count{ it.value  > 1 }
}

class Line(pointList: List<String>){
    val x1 = pointList[0].toInt(); val y1 = pointList[1].toInt(); val x2 = pointList[2].toInt(); val y2 = pointList[3].toInt()
    val isDiagonal = x1 != x2 && y1 != y2
    fun allPoints(): List<Pair<Int, Int>> {
        var xs = (minOf(x1,x2)..maxOf(x1,x2)).toList()
        if(x2<x1) xs = xs.reversed()
        var ys = (minOf(y1,y2)..maxOf(y1,y2)).toList()
        if(y2<y1) ys = ys.reversed()
        return (0..maxOf(xs.lastIndex, ys.lastIndex)).map{ i ->
            Pair(xs[minOf(i,xs.lastIndex)],ys[minOf(i,ys.lastIndex)])
        }
    }
}

//Part1 = 7436
//Part2 = 21104